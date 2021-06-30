package com.example.Gym.repository;

import com.example.Gym.model.Schedule;
import com.example.Gym.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Set;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {
    Set<Schedule> getAllByPrice(Integer cena);

    Set<Schedule> getAllBy();
    Set<Schedule> getAllByBeginDate(Date datum);
}
