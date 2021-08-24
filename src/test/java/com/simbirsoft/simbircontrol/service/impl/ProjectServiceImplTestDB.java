package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.entity.Client;
import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.Usr;
import com.simbirsoft.simbircontrol.enums.Role;
import com.simbirsoft.simbircontrol.enums.State;
import com.simbirsoft.simbircontrol.repository.ClientRepository;
import com.simbirsoft.simbircontrol.repository.ProjectRepository;
import com.simbirsoft.simbircontrol.repository.UsrRepository;
import com.simbirsoft.simbircontrol.rest.dto.ProjectResponseDto;
import com.simbirsoft.simbircontrol.service.ProjectService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:applicationTest.properties")
@Profile("test")
public class ProjectServiceImplTestDB {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UsrRepository usrRepository;

    Project project = new Project();

    @Before
    public void init() {
        Client client = new Client();
        client.setName("SimbirSoft");
        client.setNumber(101582);
        client = clientRepository.save(client);

        Usr usr = new Usr();
        usr.setRole(Role.ADMIN);
        usr.setSurname("Ivanov");
        usr.setName("Ivan");
        usr.setLogin("ivan");
        usr.setPassword("passivan");
        usr = usrRepository.save(usr);

        project.setState(State.BACKLOG);
        project.setName("ProjectTest");
        project.setClient(client);
        project.setUserLeader(usr);
        project.setPrice(456456L);
        project.setDescription("des");
        project = projectRepository.save(project);
    }

    @Test
    public void getByIdTest() {
        ProjectResponseDto expected = new ProjectResponseDto();
        expected.setId(project.getId());
        expected.setState(project.getState());
        expected.setName(project.getName());
        expected.setClientId(project.getClient().getId());
        expected.setUserIdLeader(project.getUserLeader().getId());
        expected.setPrice(project.getPrice());
        expected.setDescription(project.getDescription());

        ProjectResponseDto actual  = projectService.getById(project.getId());

        Assert.assertEquals(expected, actual);
    }


    @After
    public void close() {
        usrRepository.deleteAll();
        clientRepository.deleteAll();
        projectRepository.deleteAll();
    }
}
