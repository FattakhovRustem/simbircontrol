package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.entity.Client;
import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.Task;
import com.simbirsoft.simbircontrol.entity.User;
import com.simbirsoft.simbircontrol.enums.State;
import com.simbirsoft.simbircontrol.exception.NoEntityException;
import com.simbirsoft.simbircontrol.exception.UnfinishedTaskException;
import com.simbirsoft.simbircontrol.repository.ClientRepository;
import com.simbirsoft.simbircontrol.repository.ProjectRepository;
import com.simbirsoft.simbircontrol.repository.TaskRepository;
import com.simbirsoft.simbircontrol.repository.UserRepository;
import com.simbirsoft.simbircontrol.rest.dto.ProjectRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ProjectResponseDto;
import com.simbirsoft.simbircontrol.service.ProjectService;
import com.simbirsoft.simbircontrol.service.converter.ProjectConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {


    private final ProjectRepository projectRepository;
    private final ProjectConverter projectConverter;

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final TaskRepository taskRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectConverter projectConverter, UserRepository userRepository, ClientRepository clientRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.projectConverter = projectConverter;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.taskRepository = taskRepository;
    }

    @Transactional
    @Override
    public List<ProjectResponseDto> getAll() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(projectConverter::fromProjectToProjectResponseDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ProjectResponseDto getById(Integer id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new NoEntityException("Project not found"));
        return projectConverter.fromProjectToProjectResponseDto(project);
    }

    @Transactional
    @Override
    public ProjectResponseDto create(ProjectRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserIdLeader()).orElseThrow(() -> new NoEntityException("User not found"));
        Client client = clientRepository.findById(requestDto.getClientId()).orElseThrow(() -> new NoEntityException("Client not found"));
        Project project = projectConverter.fromProjectRequestDtoToProject(requestDto);
        project.setClient(client);
        project.setUserLeader(user);
        return projectConverter.fromProjectToProjectResponseDto(projectRepository.save(project));
    }

    @Transactional
    @Override
    public ProjectResponseDto update(ProjectRequestDto requestDto) {
        Project project = projectRepository.findById(requestDto.getId()).orElseThrow(() -> new NoEntityException("Project not found"));
        User user = userRepository.findById(requestDto.getUserIdLeader()).orElseThrow(() -> new NoEntityException("User not found"));
        Client client = clientRepository.findById(requestDto.getClientId()).orElseThrow(() -> new NoEntityException("Client not found"));



        if (requestDto.getState().name().equalsIgnoreCase(State.DONE.name())) {
            List<Task> list = taskRepository.findUnfinishedTasksByProject(project);
            if (list.size() > 0) {
                throw new UnfinishedTaskException("Tasks not finished");
            }
        }

        project = projectConverter.fromProjectRequestDtoToProject(requestDto);
        project.setUserLeader(user);
        project.setClient(client);

        return projectConverter.fromProjectToProjectResponseDto(projectRepository.save(project));
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        projectRepository.findById(id).orElseThrow(() -> new NoEntityException("Project not found"));
        projectRepository.deleteById(id);
    }
}
