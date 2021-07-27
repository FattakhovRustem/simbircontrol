package com.simbirsoft.simbircontrol.service;

import com.simbirsoft.simbircontrol.rest.dto.ReleaseRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseResponseDto;

public interface ReleaseService {

    ReleaseResponseDto getById(Integer id);

    ReleaseResponseDto create(ReleaseRequestDto requestDto);

    ReleaseResponseDto updateById(Integer id, ReleaseRequestDto requestDto);

    void deleteById(Integer id);
}
