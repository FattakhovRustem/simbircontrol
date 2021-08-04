package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.Release;
import com.simbirsoft.simbircontrol.entity.Task;
import com.simbirsoft.simbircontrol.entity.User;
import com.simbirsoft.simbircontrol.exception.NoEntityException;
import com.simbirsoft.simbircontrol.repository.ProjectRepository;
import com.simbirsoft.simbircontrol.repository.ReleaseRepository;
import com.simbirsoft.simbircontrol.repository.TaskRepository;
import com.simbirsoft.simbircontrol.repository.UserRepository;
import com.simbirsoft.simbircontrol.rest.dto.TaskRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.TaskResponseDto;
import com.simbirsoft.simbircontrol.service.TaskService;
import com.simbirsoft.simbircontrol.service.converter.TaskConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ReleaseRepository releaseRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskConverter taskConverter, UserRepository userRepository, ProjectRepository projectRepository, ReleaseRepository releaseRepository) {
        this.taskRepository = taskRepository;
        this.taskConverter = taskConverter;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.releaseRepository = releaseRepository;
    }

    @Transactional
    @Override
    public List<TaskResponseDto> getAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(taskConverter::fromTaskToTaskResponseDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public TaskResponseDto getById(Integer id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NoEntityException("Task not found"));
        return taskConverter.fromTaskToTaskResponseDto(task);
    }

    @Transactional
    @Override
    public TaskResponseDto create(TaskRequestDto requestDto) {
        Project project = projectRepository.findById(requestDto.getProjectId()).orElseThrow(() -> new NoEntityException("Project not found"));
        Release release = releaseRepository.findById(requestDto.getReleaseId()).orElseThrow(() -> new NoEntityException("Release not found"));
        User userAuthor = userRepository.findById(requestDto.getIdAuthor()).orElseThrow(() -> new NoEntityException("User-Author not found"));
        User userPerformer = userRepository.findById(requestDto.getIdPerformer()).orElseThrow(() -> new NoEntityException("User-Performer not found"));
        Task task = taskConverter.fromTaskRequestDtoToTask(requestDto);
        task.setProjectTask(project);
        task.setRelease(release);
        task.setUserAuthor(userAuthor);
        task.setUserPerformer(userPerformer);

        return taskConverter.fromTaskToTaskResponseDto(taskRepository.save(task));
    }

    @Transactional
    @Override
    public TaskResponseDto update(TaskRequestDto requestDto) {
        userRepository.findById(requestDto.getId()).orElseThrow(() -> new NoEntityException("Task not found"));
        Project project = projectRepository.findById(requestDto.getProjectId()).orElseThrow(() -> new NoEntityException("Project not found"));
        Release release = releaseRepository.findById(requestDto.getReleaseId()).orElseThrow(() -> new NoEntityException("Release not found"));
        User userAuthor = userRepository.findById(requestDto.getIdAuthor()).orElseThrow(() -> new NoEntityException("User-Author not found"));
        User userPerformer = userRepository.findById(requestDto.getIdPerformer()).orElseThrow(() -> new NoEntityException("User-Performer not found"));
        Task task = taskConverter.fromTaskRequestDtoToTask(requestDto);
        task.setProjectTask(project);
        task.setRelease(release);
        task.setUserAuthor(userAuthor);
        task.setUserPerformer(userPerformer);

        return taskConverter.fromTaskToTaskResponseDto(taskRepository.save(task));
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        taskRepository.findById(id).orElseThrow(() -> new NoEntityException("Task not found"));
        taskRepository.deleteById(id);
    }
}
