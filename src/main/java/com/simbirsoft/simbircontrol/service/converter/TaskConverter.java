package com.simbirsoft.simbircontrol.service.converter;

import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.Release;
import com.simbirsoft.simbircontrol.entity.Task;
import com.simbirsoft.simbircontrol.entity.User;
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

        Project project = new Project();
        project.setId(requestDto.getProjectId());
        task.setProjectTask(project);

        Release release = new Release();
        release.setId(requestDto.getReleaseId());
        task.setRelease(release);

        User userAuthor = new User();
        userAuthor.setId(requestDto.getIdAuthor());
        task.setUserAuthor(userAuthor);

        User userPerformer = new User();
        userPerformer.setId(requestDto.getIdPerformer());
        task.setUserPerformer(userPerformer);
        return task;
    }

    public TaskResponseDto fromTaskToTaskResponseDto(Task task) {
        TaskResponseDto responseDto = new TaskResponseDto();

        responseDto.setId(task.getId());
        responseDto.setState(task.getState());
        responseDto.setName(task.getName());
        responseDto.setDescription(task.getDescription());
        responseDto.setIdAuthor(task.getUserAuthor().getId());
        responseDto.setIdPerformer(task.getUserPerformer().getId());
        responseDto.setProjectId(task.getProjectTask().getId());
        responseDto.setReleaseId(task.getRelease().getId());

        return responseDto;
    }
}
