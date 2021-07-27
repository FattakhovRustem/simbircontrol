package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.repository.ReleaseRepository;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseResponseDto;
import com.simbirsoft.simbircontrol.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    @Autowired
    private ReleaseRepository releaseRepository;

    @Override
    public ReleaseResponseDto getById(Integer id) {
        return null;
    }

    @Override
    public ReleaseResponseDto create(ReleaseRequestDto requestDto) {
        return null;
    }

    @Override
    public ReleaseResponseDto updateById(Integer id, ReleaseRequestDto requestDto) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
