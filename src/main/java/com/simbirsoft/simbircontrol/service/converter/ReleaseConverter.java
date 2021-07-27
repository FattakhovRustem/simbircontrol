package com.simbirsoft.simbircontrol.service.converter;

import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.Release;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseResponseDto;

public class ReleaseConverter {

    public static Release fromReleaseRequestDtoToRelease(ReleaseRequestDto requestDto) {
        Release release = new Release();
        release.setDateEnd(requestDto.getDateEnd());
        release.setDateStart(requestDto.getDateStart());
        release.setVersion(requestDto.getVersion());
        release.setId(requestDto.getId());

        Project project = new Project();
        project.setId(requestDto.getId());
        release.setProjectRelease(project);
        return release;
    }

    public static ReleaseResponseDto fromReleaseToReleaseResponseDto(Release release) {
        ReleaseResponseDto responseDto = new ReleaseResponseDto();
        responseDto.setDateEnd(release.getDateEnd());
        responseDto.setDateStart(release.getDateStart());
        responseDto.setId(release.getId());
        responseDto.setVersion(release.getVersion());

        responseDto.setProjectId(release.getProjectRelease().getId());
        return responseDto;
    }
}
