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

import java.time.LocalDateTime;
import java.time.Month;

@RunWith(MockitoJUnitRunner.class)
@Profile("test")
public class ReleaseConverterTest {

    @InjectMocks
    private ReleaseConverter releaseConverter;

    private final Release release = new Release();

    private final LocalDateTime dateStart = LocalDateTime.of(2021, Month.JUNE, 30, 23, 59, 59);
    private final LocalDateTime dateEnd = LocalDateTime.of(2021, Month.AUGUST, 31, 23, 59, 59);


    @Before
    public void init() {
        Project project = new Project();
        project.setId(99);

        release.setId(890);
        release.setVersion("3.1");
        release.setDateStart(dateStart);
        release.setDateEnd(dateEnd);
        release.setProjectRelease(project);
    }

    @Test
    public void fromReleaseRequestDtoToReleaseTest() {
        ReleaseRequestDto requestDto = new ReleaseRequestDto();
        requestDto.setId(890);
        requestDto.setDateStart(dateStart);
        requestDto.setDateEnd(dateEnd);
        requestDto.setVersion("3.1");
        Release actual = releaseConverter.fromReleaseRequestDtoToRelease(requestDto);

        Assert.assertEquals(release.getId(), actual.getId());
        Assert.assertEquals(release.getVersion(), actual.getVersion());
        Assert.assertEquals(release.getDateStart(), actual.getDateStart());
        Assert.assertEquals(release.getDateEnd(), actual.getDateEnd());
    }

    @Test
    public void fromReleaseToReleaseResponseDtoTest() {
        ReleaseResponseDto expected = new ReleaseResponseDto();
        expected.setId(890);
        expected.setVersion("3.1");
        expected.setDateStart(dateStart);
        expected.setDateEnd(dateEnd);
        expected.setProjectId(99);

        ReleaseResponseDto actual = releaseConverter.fromReleaseToReleaseResponseDto(release);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getVersion(), actual.getVersion());
        Assert.assertEquals(expected.getProjectId(), actual.getProjectId());
        Assert.assertEquals(expected.getDateStart(), actual.getDateStart());
        Assert.assertEquals(expected.getDateEnd(), actual.getDateEnd());
    }
}
