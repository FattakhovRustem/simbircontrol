package com.simbirsoft.simbircontrol.repository;

import com.simbirsoft.simbircontrol.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
