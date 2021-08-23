package com.simbirsoft.simbircontrol.service.converter;

import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.Release;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseResponseDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Profile;

@RunWith(MockitoJUnitRunner.class)
@Profile("test")
public class ReleaseConverterTest {

    @InjectMocks
    private ReleaseConverter releaseConverter;

    private final Release release = new Release();

    @Before
    public void init() {
        release.setId(890);
        release.setProjectRelease(new Project());
    }

    @Test
    public void fromReleaseRequestDtoToReleaseTest() {
        ReleaseRequestDto requestDto = new ReleaseRequestDto();
        requestDto.setId(890);
        Release actual = releaseConverter.fromReleaseRequestDtoToRelease(requestDto);

        Assert.assertTrue(release.getId().equals(actual.getId()));
    }

    @Test
    public void fromReleaseToReleaseResponseDtoTest() {
        ReleaseResponseDto expected = new ReleaseResponseDto();
        expected.setId(890);
        ReleaseResponseDto actual = releaseConverter.fromReleaseToReleaseResponseDto(release);

        Assert.assertTrue(expected.getId().equals(actual.getId()));
    }
}
