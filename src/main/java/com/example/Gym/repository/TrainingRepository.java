package com.example.Gym.repository;

import com.example.Gym.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TrainingRepository extends JpaRepository<Training,Integer> {
    Set<Training> findAllBy();

    Set<Training> getAllByName(String name);

    Set<Training> getAllByType(String type);

    Set<Training> getAllByDescription(String description);
}
