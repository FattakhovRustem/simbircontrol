package com.simbirsoft.simbircontrol.repository;

import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.entity.Release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Integer> {

    /**
     * find all releases said project
     * @param project project
     * @return list releases
     */
    List<Release> findByProjectRelease(Project project);
}
