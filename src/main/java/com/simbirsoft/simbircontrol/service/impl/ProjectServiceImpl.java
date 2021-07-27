package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.repository.ProjectRepository;
import com.simbirsoft.simbircontrol.rest.dto.ProjectRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ProjectResponseDto;
import com.simbirsoft.simbircontrol.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<ProjectResponseDto> getAll() {
        return null;
    }

    @Override
    public ProjectResponseDto getById(Integer id) {
        return null;
    }

    @Override
    public ProjectResponseDto create(ProjectRequestDto requestDto) {
        return null;
    }

    @Override
    public ProjectResponseDto updateById(Integer id, ProjectRequestDto requestDto) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
