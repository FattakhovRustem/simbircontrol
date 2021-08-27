package com.simbirsoft.simbircontrol.service;

import com.simbirsoft.simbircontrol.rest.dto.TaskRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.TaskResponseDto;

import java.util.List;

public interface TaskService {

    List<TaskResponseDto> getAll();

    TaskResponseDto getById(Integer id);

    TaskResponseDto create(TaskRequestDto requestDto);

    TaskResponseDto update(TaskRequestDto requestDto);

    void deleteById(Integer id);

}
