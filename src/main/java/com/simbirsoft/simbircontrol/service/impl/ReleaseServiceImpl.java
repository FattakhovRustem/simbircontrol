package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.Release;
import com.simbirsoft.simbircontrol.exception.NoEntityException;
import com.simbirsoft.simbircontrol.repository.ProjectRepository;
import com.simbirsoft.simbircontrol.repository.ReleaseRepository;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseResponseDto;
import com.simbirsoft.simbircontrol.service.ReleaseService;
import com.simbirsoft.simbircontrol.service.converter.ReleaseConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    private final ReleaseRepository releaseRepository;
    private final ReleaseConverter releaseConverter;

    private final ProjectRepository projectRepository;

    public ReleaseServiceImpl(ReleaseRepository releaseRepository, ReleaseConverter releaseConverter, ProjectRepository projectRepository) {
        this.releaseRepository = releaseRepository;
        this.releaseConverter = releaseConverter;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ReleaseResponseDto> getReleasesProject(Integer projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new NoEntityException("Project not found"));

        List<Release> releases = releaseRepository.findByProjectRelease(project);
        return releases.stream().map(releaseConverter::fromReleaseToReleaseResponseDto).collect(Collectors.toList());
    }

    @Override
    public ReleaseResponseDto getById(Integer id) {
        releaseRepository.findById(id).orElseThrow(() -> new NoEntityException("Release not found"));
        return releaseConverter.fromReleaseToReleaseResponseDto(releaseRepository.getById(id));
    }

    @Override
    public ReleaseResponseDto create(ReleaseRequestDto requestDto) {
        Project project = projectRepository.findById(requestDto.getProjectId()).orElseThrow(() -> new NoEntityException("Project not found"));
        Release release = releaseConverter.fromReleaseRequestDtoToRelease(requestDto);
        release.setProjectRelease(project);
        return releaseConverter.fromReleaseToReleaseResponseDto(releaseRepository.save(release));
    }

    @Override
    public ReleaseResponseDto update(ReleaseRequestDto requestDto) {
        releaseRepository.findById(requestDto.getProjectId()).orElseThrow(() -> new NoEntityException("Release not found"));
        Project project = projectRepository.findById(requestDto.getProjectId()).orElseThrow(() -> new NoEntityException("Project not found"));
        Release release = releaseConverter.fromReleaseRequestDtoToRelease(requestDto);
        release.setProjectRelease(project);
        return releaseConverter.fromReleaseToReleaseResponseDto(releaseRepository.save(release));
    }

    @Override
    public void deleteById(Integer id) {
        releaseRepository.findById(id).orElseThrow(() -> new NoEntityException("Release not found"));
        releaseRepository.deleteById(id);
    }
}
