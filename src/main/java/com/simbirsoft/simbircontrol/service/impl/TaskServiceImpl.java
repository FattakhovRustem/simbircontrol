package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.entity.Task;
import com.simbirsoft.simbircontrol.repository.TaskRepository;
import com.simbirsoft.simbircontrol.rest.dto.TaskRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.TaskResponseDto;
import com.simbirsoft.simbircontrol.service.TaskService;
import com.simbirsoft.simbircontrol.service.converter.TaskConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private TaskConverter taskConverter;

    public TaskServiceImpl(TaskRepository taskRepository, TaskConverter taskConverter) {
        this.taskRepository = taskRepository;
        this.taskConverter = taskConverter;
    }

    @Override
    public List<TaskResponseDto> getAll() {
        List<TaskResponseDto> result = new ArrayList<>();
        List<Task> tasks = taskRepository.findAll();
        for (Task task : tasks) {
            result.add(taskConverter.fromTaskToTaskResponseDto(task));
        }
        return result;
    }

    @Override
    public TaskResponseDto getById(Integer id) {
        return taskConverter.fromTaskToTaskResponseDto(taskRepository.getById(id));
    }

    @Override
    public TaskResponseDto create(TaskRequestDto requestDto) {
        Task task = taskRepository.save(taskConverter.fromTaskRequestDtoToTask(requestDto));
        return taskConverter.fromTaskToTaskResponseDto(task);
    }

    @Override
    public TaskResponseDto update(TaskRequestDto requestDto) {
        Task task = taskRepository.save(taskConverter.fromTaskRequestDtoToTask(requestDto));
        return taskConverter.fromTaskToTaskResponseDto(task);
    }

    @Override
    public void deleteById(Integer id) {
        taskRepository.deleteById(id);
    }
}
