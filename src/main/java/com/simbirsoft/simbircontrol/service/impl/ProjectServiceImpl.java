package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.entity.Client;
import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.Task;
import com.simbirsoft.simbircontrol.entity.User;
import com.simbirsoft.simbircontrol.enums.State;
import com.simbirsoft.simbircontrol.exception.NoEntityException;
import com.simbirsoft.simbircontrol.exception.UnfinishedTaskException;
import com.simbirsoft.simbircontrol.repository.UserRepository;
import com.simbirsoft.simbircontrol.repository.ClientRepository;
import com.simbirsoft.simbircontrol.repository.TaskRepository;
import com.simbirsoft.simbircontrol.repository.ProjectRepository;
import com.simbirsoft.simbircontrol.repository.ReleaseRepository;
import com.simbirsoft.simbircontrol.rest.dto.ProjectResponseDto;
import com.simbirsoft.simbircontrol.rest.dto.ProjectRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.TaskRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseResponseDto;
import com.simbirsoft.simbircontrol.rest.dto.TaskResponseDto;
import com.simbirsoft.simbircontrol.service.ProjectService;
import com.simbirsoft.simbircontrol.service.TaskService;
import com.simbirsoft.simbircontrol.service.converter.ProjectConverter;
import com.simbirsoft.simbircontrol.service.converter.ReleaseConverter;
import com.simbirsoft.simbircontrol.service.converter.TaskConverter;
import com.simbirsoft.simbircontrol.service.filter.Condition;
import com.simbirsoft.simbircontrol.service.filter.TaskFilter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
    private final TaskService taskService;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectConverter projectConverter, ReleaseConverter releaseConverter, TaskConverter taskConverter, UserRepository userRepository, ClientRepository clientRepository, TaskRepository taskRepository, ReleaseRepository releaseRepository, TaskService taskService) {
        this.projectRepository = projectRepository;
        this.projectConverter = projectConverter;
        this.releaseConverter = releaseConverter;
        this.taskConverter = taskConverter;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.taskRepository = taskRepository;
        this.releaseRepository = releaseRepository;
        this.taskService = taskService;
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
    public void addTasksFromCSV(Integer id, MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            CSVParser csvParser =  CSVParser.parse(inputStream, StandardCharsets.UTF_8, CSVFormat.RFC4180);
            List<CSVRecord> records = csvParser.getRecords();
            for (CSVRecord record : records) {
                TaskRequestDto taskRequestDto = new TaskRequestDto();
                taskRequestDto.setName(record.get(0));
                taskRequestDto.setState(State.valueOf(record.get(1)));
                taskRequestDto.setDescription(record.get(2));
                taskRequestDto.setIdAuthor(Integer.valueOf(record.get(3)));
                taskRequestDto.setIdPerformer(Integer.valueOf(record.get(4)));
                taskRequestDto.setProjectId(Integer.valueOf(record.get(5)));
                taskRequestDto.setReleaseId(Integer.valueOf(record.get(6)));
                taskService.create(taskRequestDto);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Incorrect argument in record CSV-file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TaskResponseDto> getFilteredTasksProject(Integer id, List<Condition> conditions) {
        TaskFilter filter = new TaskFilter(conditions);
        return taskRepository.findAll(filter).stream().map(taskConverter::fromTaskToTaskResponseDto).collect(Collectors.toList());
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
