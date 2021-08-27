package com.simbirsoft.simbircontrol.service;

import com.simbirsoft.simbircontrol.rest.dto.ProjectRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ProjectResponseDto;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseResponseDto;
import com.simbirsoft.simbircontrol.rest.dto.TaskResponseDto;

import java.util.List;

public interface ProjectService {

    List<ProjectResponseDto> getAll();

    ProjectResponseDto getById(Integer id);

    ProjectResponseDto create(ProjectRequestDto requestDto);

    ProjectResponseDto update(ProjectRequestDto requestDto);

    void deleteById(Integer id);

    List<ReleaseResponseDto> getReleasesProject(Integer id);

    List<TaskResponseDto> getTasksProject(Integer id);
}
