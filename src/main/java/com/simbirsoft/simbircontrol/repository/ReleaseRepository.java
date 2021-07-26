package com.simbirsoft.simbircontrol.repository;

import com.simbirsoft.simbircontrol.entity.Release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Integer> {
}
