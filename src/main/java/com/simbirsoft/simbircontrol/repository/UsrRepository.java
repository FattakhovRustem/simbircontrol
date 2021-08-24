package com.simbirsoft.simbircontrol.repository;

import com.simbirsoft.simbircontrol.entity.Usr;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UsrRepository extends JpaRepository<Usr, Integer> {

    /**
     * find user with login
     * @param login user login
     * @return user
     */
    Optional<Usr> findByLogin(String login);
}
