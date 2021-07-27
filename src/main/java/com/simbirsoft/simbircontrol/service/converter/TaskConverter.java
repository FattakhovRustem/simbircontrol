package com.simbirsoft.simbircontrol.service.converter;

import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.Release;
import com.simbirsoft.simbircontrol.entity.Task;
import com.simbirsoft.simbircontrol.rest.dto.TaskRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.TaskResponseDto;

public class TaskConverter {

    public static Task fromTaskRequestDtoToTask(TaskRequestDto requestDto) {
        Task task = new Task();
        task.setId(requestDto.getId());
        task.setName(requestDto.getName());
        task.setState(requestDto.getState());
        task.setDescription(requestDto.getDescription());

        Project project = new Project();
        project.setId(requestDto.getProjectId());
        task.setProjectTask(project);

        Release release = new Release();
        release.setId(requestDto.getReleaseId());
        task.setRelease(release);
        return task;
    }

    public static TaskResponseDto fromTaskToTaskResponseDto(Task task) {
        TaskResponseDto responseDto = new TaskResponseDto();

        return responseDto;
    }
}
