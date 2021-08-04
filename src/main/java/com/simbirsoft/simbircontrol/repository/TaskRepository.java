package com.simbirsoft.simbircontrol.repository;

import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.Release;
import com.simbirsoft.simbircontrol.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("SELECT t FROM Task t WHERE t.projectTask = :projectTask and t.state <> 'DONE'")
    List<Task> findUnfinishedTasksByProject(@Param("projectTask") Project project);

    @Query(value = "SELECT count(*) FROM task t WHERE t.release_id = :releaseId and t.state != 'DONE'", nativeQuery = true)
    Integer findUnfinishedTasksByReleaseId(@Param("releaseId") Integer releaseId);
}
