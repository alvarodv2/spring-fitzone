package com.spring_fitzone.repository;

import com.spring_fitzone.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryClient extends JpaRepository<Client, Integer> {

}
