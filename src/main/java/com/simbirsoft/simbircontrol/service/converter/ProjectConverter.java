package com.simbirsoft.simbircontrol.service.converter;

import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.rest.dto.ProjectRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ProjectResponseDto;

public class ProjectConverter {

    public static Project fromProjectRequestDtoToProject(ProjectRequestDto requestDto) {
        Project project = new Project();

        return project;
    }

    public static ProjectResponseDto fromProjectToProjectResponseDto(Project project) {
        ProjectResponseDto responseDto = new ProjectResponseDto();

        return responseDto;
    }
}
