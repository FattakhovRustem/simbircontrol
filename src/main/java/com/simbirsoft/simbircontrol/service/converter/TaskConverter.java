package com.simbirsoft.simbircontrol.service.converter;

import com.simbirsoft.simbircontrol.entity.Task;
import com.simbirsoft.simbircontrol.rest.dto.TaskRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.TaskResponseDto;

public class TaskConverter {

    public static Task fromTaskRequestDtoToTask(TaskRequestDto requestDto) {
        Task task = new Task();

        return task;
    }

    public static TaskResponseDto fromTaskToTaskResponseDto(Task task) {
        TaskResponseDto responseDto = new TaskResponseDto();

        return responseDto;
    }
}
