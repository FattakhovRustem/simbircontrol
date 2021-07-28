package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.Release;
import com.simbirsoft.simbircontrol.repository.ReleaseRepository;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseResponseDto;
import com.simbirsoft.simbircontrol.service.ReleaseService;
import com.simbirsoft.simbircontrol.service.converter.ReleaseConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    private ReleaseRepository releaseRepository;
    private ReleaseConverter releaseConverter;

    public ReleaseServiceImpl(ReleaseRepository releaseRepository, ReleaseConverter releaseConverter) {
        this.releaseRepository = releaseRepository;
        this.releaseConverter = releaseConverter;
    }

    @Override
    public List<ReleaseResponseDto> getReleasesProject(Integer projectId) {
        List<ReleaseResponseDto> result = new ArrayList<>();
        Project project = new Project();
        project.setId(projectId);
        List<Release> releases = releaseRepository.findByProjectRelease(project);
        for (Release release : releases) {
            result.add(releaseConverter.fromReleaseToReleaseResponseDto(release));
        }
        return result;

    }

    @Override
    public ReleaseResponseDto getById(Integer id) {
        return releaseConverter.fromReleaseToReleaseResponseDto(releaseRepository.getById(id));
    }

    @Override
    public ReleaseResponseDto create(ReleaseRequestDto requestDto) {
        Release release = releaseRepository.save(releaseConverter.fromReleaseRequestDtoToRelease(requestDto));
        return releaseConverter.fromReleaseToReleaseResponseDto(release);
    }

    @Override
    public ReleaseResponseDto update(ReleaseRequestDto requestDto) {
        Release release = releaseRepository.save(releaseConverter.fromReleaseRequestDtoToRelease(requestDto));
        return releaseConverter.fromReleaseToReleaseResponseDto(release);
    }

    @Override
    public void deleteById(Integer id) {
        releaseRepository.deleteById(id);
    }
}
