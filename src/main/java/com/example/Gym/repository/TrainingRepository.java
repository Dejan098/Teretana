package com.example.Gym.repository;

import com.example.Gym.model.Schedule;
import com.example.Gym.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TrainingRepository extends JpaRepository<Training,Integer> {
    Set<Training> findAllBy();

    Set<Training> getAllByName(String name);

    Set<Training> getAllByNameAndType(String name,String type);
    Set<Training> getAllByNameAndDescription(String name,String description);

    Set<Training> getAllByTypeAndDescription(String name,String description);

    Set<Training> getAllByNameAndSchedule(String name,Schedule schedules);
    Set<Training> getAllByTypeAndSchedule(String name,Schedule schedules);
    Set<Training> getAllByDescriptionAndSchedule(String name,Schedule schedules);

    Set<Training> getAllByType(String type);

    Set<Training> getAllByDescription(String description);

    Training findOneBySchedule(Schedule schedule);

    Training findOneById(Integer id);
}
