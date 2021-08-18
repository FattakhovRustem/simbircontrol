package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.Release;
import com.simbirsoft.simbircontrol.exception.NoEntityException;
import com.simbirsoft.simbircontrol.repository.ProjectRepository;
import com.simbirsoft.simbircontrol.repository.ReleaseRepository;
import com.simbirsoft.simbircontrol.repository.TaskRepository;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseResponseDto;
import com.simbirsoft.simbircontrol.service.ReleaseService;
import com.simbirsoft.simbircontrol.service.converter.ReleaseConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    private final static Logger logger = LoggerFactory.getLogger(ReleaseServiceImpl.class);

    private final ReleaseRepository releaseRepository;
    private final ReleaseConverter releaseConverter;

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public ReleaseServiceImpl(ReleaseRepository releaseRepository, ReleaseConverter releaseConverter, ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.releaseRepository = releaseRepository;
        this.releaseConverter = releaseConverter;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Transactional
    @Override
    public Integer getUnfinishedTasksById(Integer id) {
        Release release = releaseRepository.findById(id).orElseThrow(() -> {
            logger.error(String.format("getUnfinishedTasksById - Release with ID = %d not found", id));
            return new NoEntityException(String.format("Release with ID = %d not found", id));
        });
        if (release.getDateEnd().compareTo(LocalDateTime.now()) >= 0) {
            return 0;
        }
        return taskRepository.findUnfinishedTasksByReleaseId(id);
    }

    @Override
    public ReleaseResponseDto getById(Integer id) {
        releaseRepository.findById(id).orElseThrow(() -> {
            logger.error(String.format("getById - Release with ID = %d not found", id));
            return new NoEntityException(String.format("Release with ID = %d not found", id));
        });
        return releaseConverter.fromReleaseToReleaseResponseDto(releaseRepository.getById(id));
    }

    @Transactional
    @Override
    public ReleaseResponseDto create(ReleaseRequestDto requestDto) {
        Project project = projectRepository.findById(requestDto.getProjectId()).orElseThrow(() -> {
            logger.error(String.format("create - Project with ID = %d not found", requestDto.getProjectId()));
            return new NoEntityException(String.format("Project with ID = %d not found", requestDto.getProjectId()));
        });
        Release release = releaseConverter.fromReleaseRequestDtoToRelease(requestDto);
        release.setProjectRelease(project);
        return releaseConverter.fromReleaseToReleaseResponseDto(releaseRepository.save(release));
    }

    @Transactional
    @Override
    public ReleaseResponseDto update(ReleaseRequestDto requestDto) {
        releaseRepository.findById(requestDto.getProjectId()).orElseThrow(() -> {
            logger.error(String.format("update - Release with ID = %d not found", requestDto.getId()));
            return new NoEntityException(String.format("Release with ID = %d not found", requestDto.getId()));
        });
        Project project = projectRepository.findById(requestDto.getProjectId()).orElseThrow(() -> {
            logger.error(String.format("update - Project with ID = %d not found", requestDto.getProjectId()));
            return new NoEntityException(String.format("Project with ID = %d not found", requestDto.getProjectId()));
        });
        Release release = releaseConverter.fromReleaseRequestDtoToRelease(requestDto);
        release.setProjectRelease(project);
        return releaseConverter.fromReleaseToReleaseResponseDto(releaseRepository.save(release));
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        releaseRepository.findById(id).orElseThrow(() -> {
            logger.error(String.format("deleteById - Release with ID = %d not found", id));
            return new NoEntityException(String.format("Release with ID = %d not found", id));
        });
        releaseRepository.deleteById(id);
    }
}
