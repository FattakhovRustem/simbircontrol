package com.simbirsoft.simbircontrol.service.converter;

import com.simbirsoft.simbircontrol.entity.Task;
import com.simbirsoft.simbircontrol.rest.dto.TaskRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.TaskResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter {

    public Task fromTaskRequestDtoToTask(TaskRequestDto requestDto) {
        Task task = new Task();
        task.setId(requestDto.getId());
        task.setName(requestDto.getName());
        task.setState(requestDto.getState());
        task.setDescription(requestDto.getDescription());
        return task;
    }

    public TaskResponseDto fromTaskToTaskResponseDto(Task task) {
        TaskResponseDto responseDto = new TaskResponseDto();

        responseDto.setId(task.getId());
        responseDto.setState(task.getState());
        responseDto.setName(task.getName());
        responseDto.setDescription(task.getDescription());
        responseDto.setIdAuthor(task.getUsrAuthor().getId());
        responseDto.setIdPerformer(task.getUsrPerformer().getId());
        responseDto.setProjectId(task.getProjectTask().getId());
        responseDto.setReleaseId(task.getRelease().getId());

        return responseDto;
    }
}
