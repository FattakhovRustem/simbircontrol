package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.entity.Release;
import com.simbirsoft.simbircontrol.repository.ReleaseRepository;
import com.simbirsoft.simbircontrol.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@Profile("test")
public class ReleaseServiceImplTest {

    @InjectMocks
    private ReleaseServiceImpl releaseService;

    @Mock
    private ReleaseRepository releaseRepository;
    @Mock
    private TaskRepository taskRepository;

    @Test
    public void getUnfinishedTasksByIdTest() {
        Integer releaseId = 10;
        Release release = new Release();
        release.setId(releaseId);
        release.setDateEnd(LocalDate.of(2021, Month.DECEMBER, 30).atStartOfDay());
        Mockito.when(releaseRepository.findById(Mockito.any())).thenReturn(Optional.of(release));

        Integer expected = 0;
        Mockito.verify(taskRepository, Mockito.never()).findUnfinishedTasksByReleaseId(releaseId);

        Integer actual = releaseService.getUnfinishedTasksById(10);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getUnfinishedTasksByIdTestMoreZero() {
        Integer releaseId = 10;
        Release release = new Release();
        release.setId(releaseId);
        release.setDateEnd(LocalDate.of(2021, Month.MAY, 30).atStartOfDay());
        Mockito.when(releaseRepository.findById(Mockito.any())).thenReturn(Optional.of(release));

        Integer expected = 4;
        Mockito.when(taskRepository.findUnfinishedTasksByReleaseId(releaseId)).thenReturn(expected);

        Integer actual = releaseService.getUnfinishedTasksById(10);
        Mockito.verify(taskRepository).findUnfinishedTasksByReleaseId(releaseId);
        Assert.assertEquals(expected, actual);
    }


}
