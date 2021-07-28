package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.repository.ProjectRepository;
import com.simbirsoft.simbircontrol.rest.dto.ProjectRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ProjectResponseDto;
import com.simbirsoft.simbircontrol.service.ProjectService;
import com.simbirsoft.simbircontrol.service.converter.ProjectConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {


    private final ProjectRepository projectRepository;
    private final ProjectConverter projectConverter;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectConverter projectConverter) {
        this.projectRepository = projectRepository;
        this.projectConverter = projectConverter;
    }

    @Override
    public List<ProjectResponseDto> getAll() {
        List<ProjectResponseDto> result = new ArrayList<>();
        List<Project> projects = projectRepository.findAll();
        for (Project project : projects) {
            result.add(projectConverter.fromProjectToProjectResponseDto(project));
        }
        return result;
    }

    @Override
    public ProjectResponseDto getById(Integer id) {
        return projectConverter.fromProjectToProjectResponseDto(projectRepository.getById(id));
    }

    @Override
    public ProjectResponseDto create(ProjectRequestDto requestDto) {
        Project project = projectRepository.save(projectConverter.fromProjectRequestDtoToProject(requestDto));
        return projectConverter.fromProjectToProjectResponseDto(project);
    }

    @Override
    public ProjectResponseDto update(ProjectRequestDto requestDto) {
        Project project = projectRepository.save(projectConverter.fromProjectRequestDtoToProject(requestDto));
        return projectConverter.fromProjectToProjectResponseDto(project);
    }

    @Override
    public void deleteById(Integer id) {
        projectRepository.deleteById(id);
    }
}
