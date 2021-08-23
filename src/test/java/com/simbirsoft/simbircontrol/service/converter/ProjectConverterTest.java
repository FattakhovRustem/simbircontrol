package com.simbirsoft.simbircontrol.service.converter;

import com.simbirsoft.simbircontrol.entity.Client;
import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.User;
import com.simbirsoft.simbircontrol.rest.dto.ProjectRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ProjectResponseDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Profile;

@RunWith(MockitoJUnitRunner.class)
@Profile("test")
public class ProjectConverterTest {

    @InjectMocks
    private ProjectConverter projectConverter;

    private Project project = new Project();

    @Before
    public void init() {
        project.setId(394);
        project.setUserLeader(new User());
        project.setClient(new Client());
    }

    @Test
    public void fromProjectRequestDtoToProjectTest() {
        ProjectRequestDto projectRequestDto = new ProjectRequestDto();
        projectRequestDto.setId(394);
        Project actual = projectConverter.fromProjectRequestDtoToProject(projectRequestDto);

        Assert.assertTrue(project.getId().equals(actual.getId()));
    }

    @Test
    public void fromProjectToProjectResponseDtoTest() {
        ProjectResponseDto expected = new ProjectResponseDto();
        expected.setId(394);
        ProjectResponseDto actual = projectConverter.fromProjectToProjectResponseDto(project);

        Assert.assertTrue(expected.getId().equals(actual.getId()));
    }
}
