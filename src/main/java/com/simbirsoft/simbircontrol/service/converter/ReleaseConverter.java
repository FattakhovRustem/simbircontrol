package com.simbirsoft.simbircontrol.service.converter;

import com.simbirsoft.simbircontrol.entity.Release;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseResponseDto;

public class ReleaseConverter {

    public static Release fromReleaseRequestDtoToRelease(ReleaseRequestDto requestDto) {
        Release release = new Release();

        return release;
    }

    public static ReleaseResponseDto fromReleaseToReleaseResponseDto(Release release) {
        ReleaseResponseDto responseDto = new ReleaseResponseDto();

        return responseDto;
    }
}
