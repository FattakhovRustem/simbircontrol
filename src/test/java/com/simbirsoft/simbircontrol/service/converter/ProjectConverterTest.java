package com.simbirsoft.simbircontrol.service.converter;

import com.simbirsoft.simbircontrol.entity.Client;
import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.Usr;
import com.simbirsoft.simbircontrol.enums.State;
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
        Usr usr = new Usr();
        usr.setId(23);
        Client client = new Client();
        client.setId(32);
        project.setId(394);
        project.setUsrLeader(usr);
        project.setClient(client);
        project.setName("SimbirSoft");
        project.setPrice(2345L);
        project.setDescription("description");
        project.setState(State.BACKLOG);

    }

    @Test
    public void fromProjectRequestDtoToProjectTest() {
        ProjectRequestDto projectRequestDto = new ProjectRequestDto();
        projectRequestDto.setId(394);
        projectRequestDto.setName("SimbirSoft");
        projectRequestDto.setPrice(2345L);
        projectRequestDto.setDescription("description");
        projectRequestDto.setState(State.BACKLOG);
        Project actual = projectConverter.fromProjectRequestDtoToProject(projectRequestDto);

        Assert.assertEquals(project.getId(), actual.getId());
        Assert.assertEquals(project.getName(), actual.getName());
        Assert.assertEquals(project.getPrice(), actual.getPrice());
        Assert.assertEquals(project.getDescription(), actual.getDescription());
        Assert.assertEquals(project.getState(), actual.getState());
    }

    @Test
    public void fromProjectToProjectResponseDtoTest() {
        ProjectResponseDto expected = new ProjectResponseDto();
        expected.setId(394);
        expected.setUsrIdLeader(23);
        expected.setClientId(32);
        expected.setName("SimbirSoft");
        expected.setPrice(2345L);
        expected.setDescription("description");
        expected.setState(State.BACKLOG);

        ProjectResponseDto actual = projectConverter.fromProjectToProjectResponseDto(project);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getUsrIdLeader(), actual.getUsrIdLeader());
        Assert.assertEquals(expected.getClientId(), actual.getClientId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getState(), actual.getState());
    }
}
