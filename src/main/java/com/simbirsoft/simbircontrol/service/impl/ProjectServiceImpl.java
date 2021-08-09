package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.entity.Client;
import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.Task;
import com.simbirsoft.simbircontrol.entity.User;
import com.simbirsoft.simbircontrol.enums.State;
import com.simbirsoft.simbircontrol.exception.NoEntityException;
import com.simbirsoft.simbircontrol.exception.UnfinishedTaskException;
import com.simbirsoft.simbircontrol.repository.*;
import com.simbirsoft.simbircontrol.rest.dto.ProjectRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ProjectResponseDto;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseResponseDto;
import com.simbirsoft.simbircontrol.rest.dto.TaskResponseDto;
import com.simbirsoft.simbircontrol.service.ProjectService;
import com.simbirsoft.simbircontrol.service.converter.ProjectConverter;
import com.simbirsoft.simbircontrol.service.converter.ReleaseConverter;
import com.simbirsoft.simbircontrol.service.converter.TaskConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {


    private final ProjectRepository projectRepository;
    private final ProjectConverter projectConverter;
    private final ReleaseConverter releaseConverter;
    private final TaskConverter taskConverter;

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final TaskRepository taskRepository;
    private final ReleaseRepository releaseRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectConverter projectConverter, ReleaseConverter releaseConverter, TaskConverter taskConverter, UserRepository userRepository, ClientRepository clientRepository, TaskRepository taskRepository, ReleaseRepository releaseRepository) {
        this.projectRepository = projectRepository;
        this.projectConverter = projectConverter;
        this.releaseConverter = releaseConverter;
        this.taskConverter = taskConverter;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.taskRepository = taskRepository;
        this.releaseRepository = releaseRepository;
    }

    @Transactional
    @Override
    public List<ProjectResponseDto> getAll() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(projectConverter::fromProjectToProjectResponseDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<ReleaseResponseDto> getReleasesProject(Integer id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new NoEntityException("Project with ID = " + id + " not found"));
        return releaseRepository.findByProjectRelease(project).stream().map(releaseConverter::fromReleaseToReleaseResponseDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<TaskResponseDto> getTasksProject(Integer id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new NoEntityException("Project with ID = " + id + " not found"));
        return taskRepository.findByProjectTask(project).stream().map(taskConverter::fromTaskToTaskResponseDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ProjectResponseDto getById(Integer id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new NoEntityException("Project with ID = " + id + " not found"));
        return projectConverter.fromProjectToProjectResponseDto(project);
    }

    @Transactional
    @Override
    public ProjectResponseDto create(ProjectRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserIdLeader()).orElseThrow(() -> new NoEntityException("UserLeader with ID = " + requestDto.getUserIdLeader() + " not found"));
        Client client = clientRepository.findById(requestDto.getClientId()).orElseThrow(() -> new NoEntityException("Client with ID = " + requestDto.getClientId() + " not found"));
        Project project = projectConverter.fromProjectRequestDtoToProject(requestDto);
        project.setClient(client);
        project.setUserLeader(user);
        return projectConverter.fromProjectToProjectResponseDto(projectRepository.save(project));
    }

    @Transactional
    @Override
    public ProjectResponseDto update(ProjectRequestDto requestDto) {
        Project project = projectRepository.findById(requestDto.getId()).orElseThrow(() -> new NoEntityException("Project with ID = " + requestDto.getId() + " not found"));
        User user = userRepository.findById(requestDto.getUserIdLeader()).orElseThrow(() -> new NoEntityException("UserLeader with ID = " + requestDto.getUserIdLeader() + " not found"));
        Client client = clientRepository.findById(requestDto.getClientId()).orElseThrow(() -> new NoEntityException("Client with ID = " + requestDto.getClientId() + " not found"));



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
        projectRepository.findById(id).orElseThrow(() -> new NoEntityException("Project with ID = " + id + " not found"));
        projectRepository.deleteById(id);
    }
}
