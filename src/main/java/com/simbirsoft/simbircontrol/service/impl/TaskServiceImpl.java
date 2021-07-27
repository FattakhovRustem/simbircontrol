package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.repository.TaskRepository;
import com.simbirsoft.simbircontrol.rest.dto.TaskRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.TaskResponseDto;
import com.simbirsoft.simbircontrol.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskResponseDto> getAll() {
        return null;
    }

    @Override
    public TaskResponseDto getById(Integer id) {
        return null;
    }

    @Override
    public TaskResponseDto create(TaskRequestDto requestDto) {
        return null;
    }

    @Override
    public TaskResponseDto updateById(Integer id, TaskRequestDto requestDto) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
