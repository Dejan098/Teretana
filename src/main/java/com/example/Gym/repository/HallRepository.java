package com.example.Gym.repository;

import com.example.Gym.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface HallRepository extends JpaRepository<Hall,Integer> {

    Hall findOneById(Integer sala);
}
