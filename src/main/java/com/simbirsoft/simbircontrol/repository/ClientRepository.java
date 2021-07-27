package com.simbirsoft.simbircontrol.repository;

import com.simbirsoft.simbircontrol.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}