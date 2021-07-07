package com.example.Gym.repository;

import com.example.Gym.model.FitnessCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FitnessCenterRepository extends JpaRepository<FitnessCenter,Integer> {
    Set<FitnessCenter> findAllBy();

    FitnessCenter findOneById(Integer id);
}
