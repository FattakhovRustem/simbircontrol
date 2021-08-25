package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.entity.Client;
import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.Task;
import com.simbirsoft.simbircontrol.entity.Usr;
import com.simbirsoft.simbircontrol.enums.State;
import com.simbirsoft.simbircontrol.exception.NoMoneyClientException;
import com.simbirsoft.simbircontrol.exception.UnfinishedTaskException;
import com.simbirsoft.simbircontrol.feign.BankClient;
import com.simbirsoft.simbircontrol.feign.dto.AccountResponseDto;
import com.simbirsoft.simbircontrol.feign.dto.DetailRequestDto;
import com.simbirsoft.simbircontrol.repository.ClientRepository;
import com.simbirsoft.simbircontrol.repository.ProjectRepository;
import com.simbirsoft.simbircontrol.repository.TaskRepository;
import com.simbirsoft.simbircontrol.repository.UsrRepository;
import com.simbirsoft.simbircontrol.rest.dto.ProjectRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ProjectResponseDto;
import com.simbirsoft.simbircontrol.service.converter.ProjectConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@Profile("test")
public class ProjectServiceImplTest {

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private UsrRepository usrRepository;
    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectConverter projectConverter;

    @Mock
    private BankClient bankClient;


    Project project = new Project();
    Usr usr = new Usr();
    Client client = new Client();
    Project projectNew = new Project();

    ProjectResponseDto expected = new ProjectResponseDto();
    ProjectRequestDto requestDto = new ProjectRequestDto();

    @Before
    public void beforeTest() {
        project.setPrice(100000L);
        Mockito.when(projectRepository.findById(Mockito.any())).thenReturn(Optional.of(project));
        Mockito.when(usrRepository.findById(Mockito.any())).thenReturn(Optional.of(usr));
        Mockito.when(clientRepository.findById(Mockito.any())).thenReturn(Optional.of(client));


        Mockito.when(projectRepository.save(Mockito.any())).thenReturn(projectNew);
        Mockito.when(projectConverter.fromProjectRequestDtoToProject(Mockito.any())).thenReturn(projectNew);

        expected.setId(11);
        expected.setUsrIdLeader(15);
        expected.setClientId(23);
        expected.setName("SimbirSoft");
        expected.setPrice(100000L);
        expected.setDescription("des");
        Mockito.when(projectConverter.fromProjectToProjectResponseDto(Mockito.any())).thenReturn(expected);

        requestDto.setId(11);
        requestDto.setUsrIdLeader(15);
        requestDto.setClientId(23);
        requestDto.setName("SimbirSoft");
        requestDto.setPrice(100000L);
        requestDto.setDescription("des");
    }

    @Test
    public void updateTestDoneWithFinishedTasks() {

        // Поиск задач по БД
        List<Task> tasks = new ArrayList<>();
        Mockito.when(taskRepository.findUnfinishedTasksByProject(Mockito.any())).thenReturn(tasks);

        requestDto.setState(State.DONE);
        ProjectResponseDto actual = projectService.update(requestDto);

        // Метод должен быть вызван 1 раз
        Mockito.verify(taskRepository).findUnfinishedTasksByProject(Mockito.any());

        Assert.assertTrue(expected.equals(actual));
    }

    @Test(expected = UnfinishedTaskException.class)
    public void updateTestStateDoneWithUnfinishedTasks() {
        // Поиск задач по БД
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        tasks.add(new Task());
        Mockito.when(taskRepository.findUnfinishedTasksByProject(Mockito.any())).thenReturn(tasks);

        requestDto.setState(State.DONE);
        ProjectResponseDto actual = projectService.update(requestDto);

        // Метод должен быть вызван 1 раз
        Mockito.verify(taskRepository).findUnfinishedTasksByProject(Mockito.any());
        // Не должен быть вызван
        Mockito.verify(bankClient, Mockito.never()).getBalanceAccount(Mockito.any());

        Assert.assertTrue(expected.equals(actual));
    }

    @Test
    public void updateTestStateInProgressCheckMoneyClient() {

        AccountResponseDto accountResponseDto = new AccountResponseDto();
        accountResponseDto.setBalance(100001L);
        Mockito.when(bankClient.getBalanceAccount(Mockito.any())).thenReturn(accountResponseDto);
        Mockito.when(bankClient.makeOperation(Mockito.any(), Mockito.any())).thenReturn(null);

        requestDto.setState(State.IN_PROGRESS);
        ProjectResponseDto actual = projectService.update(requestDto);

        // Должен быть вызван 1 раз
        Mockito.verify(bankClient).getBalanceAccount(Mockito.any());
        // Не должен быть вызван
        Mockito.verify(taskRepository, Mockito.never()).findUnfinishedTasksByProject(Mockito.any());

        Assert.assertTrue(expected.equals(actual));
    }

}
