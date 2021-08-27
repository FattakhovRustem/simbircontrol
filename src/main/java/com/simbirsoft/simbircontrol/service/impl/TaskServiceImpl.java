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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final static Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

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
        Task task = taskRepository.findById(id).orElseThrow(() -> {
            logger.error(String.format("getById - Task with ID = %d not found", id));
            return new NoEntityException(String.format("Task with ID = %d not found", id));
        });
        return taskConverter.fromTaskToTaskResponseDto(task);
    }

    @Transactional
    @Override
    public TaskResponseDto create(TaskRequestDto requestDto) {
        Project project = projectRepository.findById(requestDto.getProjectId()).orElseThrow(() -> {
            logger.error(String.format("update - Project with ID = %d not found", requestDto.getProjectId()));
            return new NoEntityException(String.format("Project with ID = %d not found", requestDto.getProjectId()));
        });
        Release release = releaseRepository.findById(requestDto.getReleaseId()).orElseThrow(() -> {
            logger.error(String.format("update - Release with ID = %d not found", requestDto.getReleaseId()));
            return new NoEntityException(String.format("Release with ID = %d not found", requestDto.getReleaseId()));
        });
        User userAuthor = userRepository.findById(requestDto.getIdAuthor()).orElseThrow(() -> {
            logger.error(String.format("update - User-Author with ID = %d not found", requestDto.getIdAuthor()));
            return new NoEntityException(String.format("User-Author with ID = %d not found", requestDto.getIdAuthor()));
        });
        User userPerformer = userRepository.findById(requestDto.getIdPerformer()).orElseThrow(() -> {
            logger.error(String.format("update - User-Performer with ID = %d not found", requestDto.getIdPerformer()));
            return new NoEntityException(String.format("User-Performer with ID = %d not found", requestDto.getIdPerformer()));
        });
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
        taskRepository.findById(requestDto.getId()).orElseThrow(() -> {
            logger.error(String.format("update - Task with ID = %d not found", requestDto.getId()));
            return new NoEntityException(String.format("Task with ID = %d not found", requestDto.getId()));
        });
        Project project = projectRepository.findById(requestDto.getProjectId()).orElseThrow(() -> {
            logger.error(String.format("update - Project with ID = %d not found", requestDto.getProjectId()));
            return new NoEntityException(String.format("Project with ID = %d not found", requestDto.getProjectId()));
        });
        Release release = releaseRepository.findById(requestDto.getReleaseId()).orElseThrow(() -> {
            logger.error(String.format("update - Release with ID = %d not found", requestDto.getReleaseId()));
            return new NoEntityException(String.format("Release with ID = %d not found", requestDto.getReleaseId()));
        });
        User userAuthor = userRepository.findById(requestDto.getIdAuthor()).orElseThrow(() -> {
            logger.error(String.format("update - User-Author with ID = %d not found", requestDto.getIdAuthor()));
            return new NoEntityException(String.format("User-Author with ID = %d not found", requestDto.getIdAuthor()));
        });
        User userPerformer = userRepository.findById(requestDto.getIdPerformer()).orElseThrow(() -> {
            logger.error(String.format("update - User-Performer with ID = %d not found", requestDto.getIdPerformer()));
            return new NoEntityException(String.format("User-Performer with ID = %d not found", requestDto.getIdPerformer()));
        });

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
        taskRepository.findById(id).orElseThrow(() -> {
            logger.error(String.format("deleteById - Task with ID = %d not found", id));
            return new NoEntityException(String.format("Task with ID = %d not found", id));
        });
        taskRepository.deleteById(id);
    }
}
