package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.Release;
import com.simbirsoft.simbircontrol.entity.Task;
import com.simbirsoft.simbircontrol.entity.Usr;
import com.simbirsoft.simbircontrol.exception.NoEntityException;
import com.simbirsoft.simbircontrol.repository.ProjectRepository;
import com.simbirsoft.simbircontrol.repository.ReleaseRepository;
import com.simbirsoft.simbircontrol.repository.TaskRepository;
import com.simbirsoft.simbircontrol.repository.UsrRepository;
import com.simbirsoft.simbircontrol.rest.dto.TaskRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.TaskResponseDto;
import com.simbirsoft.simbircontrol.service.TaskService;
import com.simbirsoft.simbircontrol.service.converter.TaskConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final static Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;

    private final UsrRepository usrRepository;
    private final ProjectRepository projectRepository;
    private final ReleaseRepository releaseRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskConverter taskConverter, UsrRepository usrRepository, ProjectRepository projectRepository, ReleaseRepository releaseRepository) {
        this.taskRepository = taskRepository;
        this.taskConverter = taskConverter;
        this.usrRepository = usrRepository;
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
            return new NoEntityException(String.format(ResourceBundle.getBundle("resource").getString("taskNotFound"), id));
        });
        return taskConverter.fromTaskToTaskResponseDto(task);
    }

    @Transactional
    @Override
    public TaskResponseDto create(TaskRequestDto requestDto) {
        Integer projectIdFromRequest = requestDto.getProjectId();
        Integer releaseIdFromRequest = requestDto.getReleaseId();
        Integer usrAuthorIdFromRequest = requestDto.getIdAuthor();
        Integer usrPerformerIdFromRequest = requestDto.getIdPerformer();


        Project project = projectRepository.findById(projectIdFromRequest).orElseThrow(() -> {
            logger.error(String.format("update - Project with ID = %d not found", projectIdFromRequest));
            return new NoEntityException(String.format(ResourceBundle.getBundle("resource").getString("projectNotFound"), projectIdFromRequest));
        });
        Release release = releaseRepository.findById(releaseIdFromRequest).orElseThrow(() -> {
            logger.error(String.format("update - Release with ID = %d not found", releaseIdFromRequest));
            return new NoEntityException(String.format(ResourceBundle.getBundle("resource").getString("releaseNotFound"), releaseIdFromRequest));
        });
        Usr usrAuthor = usrRepository.findById(usrAuthorIdFromRequest).orElseThrow(() -> {
            logger.error(String.format("update - Usr-Author with ID = %d not found", usrAuthorIdFromRequest));
            return new NoEntityException(String.format(ResourceBundle.getBundle("resource").getString("usrAuthorNotFound"), usrAuthorIdFromRequest));
        });
        Usr usrPerformer = usrRepository.findById(usrPerformerIdFromRequest).orElseThrow(() -> {
            logger.error(String.format("update - Usr-Performer with ID = %d not found", usrPerformerIdFromRequest));
            return new NoEntityException(String.format(ResourceBundle.getBundle("resource").getString("usrPerformerNotFound"), usrPerformerIdFromRequest));
        });
        Task task = taskConverter.fromTaskRequestDtoToTask(requestDto);
        task.setProjectTask(project);
        task.setRelease(release);
        task.setUsrAuthor(usrAuthor);
        task.setUsrPerformer(usrPerformer);

        return taskConverter.fromTaskToTaskResponseDto(taskRepository.save(task));
    }

    @Transactional
    @Override
    public TaskResponseDto update(TaskRequestDto requestDto) {
        Integer projectIdFromRequest = requestDto.getProjectId();
        Integer releaseIdFromRequest = requestDto.getReleaseId();
        Integer usrAuthorIdFromRequest = requestDto.getIdAuthor();
        Integer usrPerformerIdFromRequest = requestDto.getIdPerformer();

        taskRepository.findById(requestDto.getId()).orElseThrow(() -> {
            logger.error(String.format("update - Task with ID = %d not found", requestDto.getId()));
            return new NoEntityException(String.format(ResourceBundle.getBundle("resource").getString("taskNotFound"), requestDto.getId()));
        });
        Project project = projectRepository.findById(projectIdFromRequest).orElseThrow(() -> {
            logger.error(String.format("update - Project with ID = %d not found", projectIdFromRequest));
            return new NoEntityException(String.format(ResourceBundle.getBundle("resource").getString("projectNotFound"), projectIdFromRequest));
        });
        Release release = releaseRepository.findById(releaseIdFromRequest).orElseThrow(() -> {
            logger.error(String.format("update - Release with ID = %d not found", releaseIdFromRequest));
            return new NoEntityException(String.format(ResourceBundle.getBundle("resource").getString("releaseNotFound"), releaseIdFromRequest));
        });
        Usr usrAuthor = usrRepository.findById(usrAuthorIdFromRequest).orElseThrow(() -> {
            logger.error(String.format("update - Usr-Author with ID = %d not found", usrAuthorIdFromRequest));
            return new NoEntityException(String.format(ResourceBundle.getBundle("resource").getString("usrAuthorNotFound"), usrAuthorIdFromRequest));
        });
        Usr usrPerformer = usrRepository.findById(usrPerformerIdFromRequest).orElseThrow(() -> {
            logger.error(String.format("update - Usr-Performer with ID = %d not found", usrPerformerIdFromRequest));
            return new NoEntityException(String.format(ResourceBundle.getBundle("resource").getString("usrPerformerNotFound"), usrPerformerIdFromRequest));
        });

        Task task = taskConverter.fromTaskRequestDtoToTask(requestDto);
        task.setProjectTask(project);
        task.setRelease(release);
        task.setUsrAuthor(usrAuthor);
        task.setUsrPerformer(usrPerformer);

        return taskConverter.fromTaskToTaskResponseDto(taskRepository.save(task));
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        taskRepository.findById(id).orElseThrow(() -> {
            logger.error(String.format("deleteById - Task with ID = %d not found", id));
            return new NoEntityException(String.format(ResourceBundle.getBundle("resource").getString("taskNotFound"), id));
        });
        taskRepository.deleteById(id);
    }
}
