package com.simbirsoft.simbircontrol.service;

import com.simbirsoft.simbircontrol.rest.dto.TaskRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.TaskResponseDto;

import java.util.List;

public interface TaskService {

    /**
     * get all tasks
     * @return list tasks
     */
    List<TaskResponseDto> getAll();

    /**
     * get task by him id
     * @param id id tasm
     * @return task
     */
    TaskResponseDto getById(Integer id);

    /**
     * create new task
     * @param requestDto properties new task
     * @return new task
     */
    TaskResponseDto create(TaskRequestDto requestDto);

    /**
     * update old task new values
     * @param requestDto new values task
     * @return task
     */
    TaskResponseDto update(TaskRequestDto requestDto);

    /**
     * delete task by him id
     * @param id id task
     */
    void deleteById(Integer id);

}
