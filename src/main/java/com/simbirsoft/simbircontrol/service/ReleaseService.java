package com.simbirsoft.simbircontrol.service;

import com.simbirsoft.simbircontrol.rest.dto.ReleaseRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseResponseDto;

import java.util.List;

public interface ReleaseService {

    List<ReleaseResponseDto> getReleasesProject(Integer projectId);

    Integer getUnfinishedTasksById(Integer id);

    ReleaseResponseDto getById(Integer id);

    ReleaseResponseDto create(ReleaseRequestDto requestDto);

    ReleaseResponseDto update(ReleaseRequestDto requestDto);

    void deleteById(Integer id);
}
