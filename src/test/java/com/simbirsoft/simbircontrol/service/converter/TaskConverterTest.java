package com.simbirsoft.simbircontrol.service.converter;

import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.Release;
import com.simbirsoft.simbircontrol.entity.Task;
import com.simbirsoft.simbircontrol.entity.Usr;
import com.simbirsoft.simbircontrol.enums.State;
import com.simbirsoft.simbircontrol.rest.dto.TaskRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.TaskResponseDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Profile;

@RunWith(MockitoJUnitRunner.class)
@Profile("test")
public class TaskConverterTest {

    @InjectMocks
    private TaskConverter taskConverter;

    private final Task task = new Task();

    @Before
    public void init() {
        Usr author = new Usr();
        author.setId(37);
        Usr performer = new Usr();
        performer.setId(54);
        Release release = new Release();
        release.setId(45);
        Project project = new Project();
        project.setId(2);

        task.setId(123);
        task.setName("Task123");
        task.setUsrPerformer(performer);
        task.setUsrAuthor(author);
        task.setRelease(release);
        task.setProjectTask(project);
        task.setState(State.IN_PROGRESS);
        task.setDescription("des");
    }

    @Test
    public void fromTaskRequestDtoToTaskTest() {
        TaskRequestDto taskRequestDto = new TaskRequestDto();
        taskRequestDto.setId(123);
        taskRequestDto.setName("Task123");
        taskRequestDto.setState(State.IN_PROGRESS);
        taskRequestDto.setDescription("des");
        Task actual = taskConverter.fromTaskRequestDtoToTask(taskRequestDto);

        Assert.assertEquals(task.getId(), actual.getId());
        Assert.assertEquals(task.getName(), actual.getName());
        Assert.assertEquals(task.getState(), actual.getState());
        Assert.assertEquals(task.getDescription(), actual.getDescription());
    }

    @Test
    public void fromTaskToTaskResponseDtoTest() {
        TaskResponseDto expected = new TaskResponseDto();
        expected.setId(123);
        expected.setName("Task123");
        expected.setState(State.IN_PROGRESS);
        expected.setDescription("des");
        expected.setIdPerformer(54);
        expected.setIdAuthor(37);
        expected.setReleaseId(45);
        expected.setProjectId(2);

        TaskResponseDto actual = taskConverter.fromTaskToTaskResponseDto(task);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getState(), actual.getState());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getIdPerformer(), actual.getIdPerformer());
        Assert.assertEquals(expected.getIdAuthor(), actual.getIdAuthor());
        Assert.assertEquals(expected.getReleaseId(), actual.getReleaseId());
        Assert.assertEquals(expected.getProjectId(), actual.getProjectId());
    }
}
