package com.simbirsoft.simbircontrol.service;

import com.simbirsoft.simbircontrol.rest.dto.ProjectRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ProjectResponseDto;

import java.util.List;

public interface ProjectService {

    List<ProjectResponseDto> getAll();

    ProjectResponseDto getById(Integer id);

    ProjectResponseDto create(ProjectRequestDto requestDto);

    ProjectResponseDto update(ProjectRequestDto requestDto);

    void deleteById(Integer id);
}
