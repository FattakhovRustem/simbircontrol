package com.simbirsoft.simbircontrol.service.converter;

import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.Release;
import com.simbirsoft.simbircontrol.entity.Task;
import com.simbirsoft.simbircontrol.entity.User;
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
        task.setId(123);
        task.setName("Task123");
        task.setUserPerformer(new User());
        task.setUserAuthor(new User());
        task.setRelease(new Release());
        task.setProjectTask(new Project());
    }

    @Test
    public void fromTaskRequestDtoToTaskTest() {
        TaskRequestDto taskRequestDto = new TaskRequestDto();
        taskRequestDto.setId(123);
        taskRequestDto.setName("Task123");
        Task actual = taskConverter.fromTaskRequestDtoToTask(taskRequestDto);

        Assert.assertEquals(task, actual);
    }

    @Test
    public void fromTaskToTaskResponseDtoTest() {
        TaskResponseDto expected = new TaskResponseDto();
        expected.setId(123);
        expected.setName("Task123");
        TaskResponseDto actual = taskConverter.fromTaskToTaskResponseDto(task);

        Assert.assertEquals(expected, actual);
    }
}
