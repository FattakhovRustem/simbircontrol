package com.simbirsoft.simbircontrol.repository;

import com.simbirsoft.simbircontrol.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * find user with login
     * @param login user login
     * @return user
     */
    Optional<User> findByLogin(String login);
}
