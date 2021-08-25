package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.Task;
import com.simbirsoft.simbircontrol.entity.Release;
import com.simbirsoft.simbircontrol.entity.Client;
import com.simbirsoft.simbircontrol.entity.Usr;
import com.simbirsoft.simbircontrol.enums.Role;
import com.simbirsoft.simbircontrol.enums.State;
import com.simbirsoft.simbircontrol.repository.ProjectRepository;
import com.simbirsoft.simbircontrol.repository.TaskRepository;
import com.simbirsoft.simbircontrol.repository.ReleaseRepository;
import com.simbirsoft.simbircontrol.repository.ClientRepository;
import com.simbirsoft.simbircontrol.repository.UsrRepository;
import com.simbirsoft.simbircontrol.rest.dto.ProjectResponseDto;
import com.simbirsoft.simbircontrol.rest.dto.TaskResponseDto;
import com.simbirsoft.simbircontrol.service.ProjectService;
import com.simbirsoft.simbircontrol.service.filter.Comparison;
import com.simbirsoft.simbircontrol.service.filter.Condition;
import com.simbirsoft.simbircontrol.service.filter.TaskFilter;
import com.simbirsoft.simbircontrol.service.filter.Type;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private ReleaseRepository releaseRepository;

    @Autowired
    private TaskRepository taskRepository;

    Project project = new Project();
    Usr usrAuthor = new Usr();
    Usr usrPerformer = new Usr();
    Release release = new Release();
    Task task = new Task();

    @Before
    public void init() {
        Client client = new Client();
        client.setName("SimbirSoft");
        client.setNumber(101582);
        client = clientRepository.save(client);

        Usr usr = new Usr();
        usr.setRole(Role.LEADER);
        usr.setSurname("Ivanov");
        usr.setName("Ivan");
        usr.setLogin("ivan");
        usr.setPassword("passivan");
        usr = usrRepository.save(usr);

        usrAuthor.setRole(Role.AUTHOR);
        usrAuthor.setSurname("Ivanov");
        usrAuthor.setName("Ivan");
        usrAuthor.setLogin("ivan");
        usrAuthor.setPassword("passivan");
        usrAuthor = usrRepository.save(usrAuthor);

        usrPerformer.setRole(Role.PERFORMER);
        usrPerformer.setSurname("Ivanov");
        usrPerformer.setName("Ivan");
        usrPerformer.setLogin("ivan");
        usrPerformer.setPassword("passivan");
        usrPerformer = usrRepository.save(usrPerformer);

        project.setState(State.BACKLOG);
        project.setName("ProjectTest");
        project.setClient(client);
        project.setUsrLeader(usr);
        project.setPrice(456456L);
        project.setDescription("des");
        project = projectRepository.save(project);

        release.setVersion("1.0");
        release.setProjectRelease(project);
        release.setDateEnd(LocalDateTime.now());
        release.setDateEnd(LocalDateTime.now());
        releaseRepository.save(release);


        task.setRelease(release);
        task.setProjectTask(project);
        task.setUsrAuthor(usrAuthor);
        task.setUsrPerformer(usrPerformer);
        task.setState(State.IN_PROGRESS);
        task = taskRepository.save(task);
    }

    @Test
    public void getByIdTest() {
        ProjectResponseDto expected = new ProjectResponseDto();
        expected.setId(project.getId());
        expected.setState(project.getState());
        expected.setName(project.getName());
        expected.setClientId(project.getClient().getId());
        expected.setUsrIdLeader(project.getUsrLeader().getId());
        expected.setPrice(project.getPrice());
        expected.setDescription(project.getDescription());

        ProjectResponseDto actual  = projectService.getById(project.getId());

        Assert.assertEquals(expected, actual);
    }

    @Transactional
    @Test
    public void addTasksFromCSVTest() {

        Path path = Paths.get("target/classes/file.csv");

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path.toFile())));
            writer.write("Task100,BACKLOG,des100," + usrAuthor.getId() + "," + usrPerformer.getId() + "," + project.getId() + "," + release.getId() + "\n");
            writer.write("Task200,BACKLOG,des200," + usrAuthor.getId() + "," + usrPerformer.getId() + "," + project.getId() + "," + release.getId() + "\n");
            writer.write("Task300,BACKLOG,des300," + usrAuthor.getId() + "," + usrPerformer.getId() + "," + project.getId() + "," + release.getId() + "\n");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MultipartFile file = new MockMultipartFile("file.csv", "file.csv", "text/plain", content);
        projectService.addTasksFromCSV(0, file);

        Task expected1 = new Task();
        expected1.setName("Task100");
        expected1.setProjectTask(project);
        expected1.setUsrAuthor(usrAuthor);
        expected1.setUsrPerformer(usrPerformer);
        expected1.setId(task.getId() + 1);

        Task expected2 = new Task();
        expected2.setName("Task200");
        expected2.setProjectTask(project);
        expected2.setUsrAuthor(usrAuthor);
        expected2.setUsrPerformer(usrPerformer);
        expected2.setId(task.getId() + 2);

        Task expected3 = new Task();
        expected3.setName("Task300");
        expected3.setProjectTask(project);
        expected3.setUsrAuthor(usrAuthor);
        expected3.setUsrPerformer(usrPerformer);
        expected3.setId(task.getId() + 3);


        Task actual1 = taskRepository.getById(task.getId() + 1);
        Task actual2 = taskRepository.getById(task.getId() + 2);
        Task actual3 = taskRepository.getById(task.getId() + 3);
        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
        Assert.assertEquals(expected3, actual3);
    }

    @Transactional
    @Test
    public void getFilteredTasksProjectTest() {
        List<Condition> conditions = new ArrayList<>();
        Condition condition1 = new Condition();
        condition1.setType(Type.STATE);
        condition1.setValue("IN_PROGRESS");
        condition1.setField("state");
        condition1.setComparison(Comparison.EQ);
        conditions.add(condition1);

        Task task = new Task();
        task.setRelease(release);
        task.setProjectTask(project);
        task.setUsrAuthor(usrAuthor);
        task.setUsrPerformer(usrPerformer);
        task.setState(State.IN_PROGRESS);
        taskRepository.save(task);

        List<TaskResponseDto> actuals = projectService.getFilteredTasksProject(0, conditions);
        int expectedCount = 2;

        Assert.assertEquals(expectedCount, actuals.size());
        Assert.assertEquals(State.IN_PROGRESS, actuals.get(0).getState());
        Assert.assertEquals(State.IN_PROGRESS, actuals.get(1).getState());
    }


    @After
    public void close() {
        taskRepository.deleteAll();
        releaseRepository.deleteAll();
        projectRepository.deleteAll();
        clientRepository.deleteAll();
        usrRepository.deleteAll();
    }
}
