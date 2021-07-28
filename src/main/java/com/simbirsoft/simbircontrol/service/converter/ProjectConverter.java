package com.simbirsoft.simbircontrol.service.converter;

import com.simbirsoft.simbircontrol.entity.Client;
import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.User;
import com.simbirsoft.simbircontrol.rest.dto.ProjectRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ProjectResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter {

    public Project fromProjectRequestDtoToProject(ProjectRequestDto requestDto) {
        Project project = new Project();
        project.setId(requestDto.getId());
        project.setState(requestDto.getState());
        project.setName(requestDto.getName());
        project.setDescription(requestDto.getDescription());

        User user = new User();
        user.setId(requestDto.getUserIdLeader());
        project.setUserLeader(user);

        Client client = new Client();
        client.setId(requestDto.getClientId());
        project.setClient(client);
        return project;
    }

    public ProjectResponseDto fromProjectToProjectResponseDto(Project project) {
        ProjectResponseDto responseDto = new ProjectResponseDto();
        responseDto.setUserIdLeader(project.getUserLeader().getId());
        responseDto.setState(project.getState());
        responseDto.setName(project.getName());
        responseDto.setId(project.getId());
        responseDto.setDescription(project.getDescription());
        responseDto.setClientId(project.getClient().getId());
        return responseDto;
    }
}
