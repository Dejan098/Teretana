package com.example.Gym.repository;

import com.example.Gym.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training,Integer> {
}
