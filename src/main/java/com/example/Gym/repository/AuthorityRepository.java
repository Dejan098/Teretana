package com.example.Gym.repository;

import com.example.Gym.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Authority findByName(String name);

}
