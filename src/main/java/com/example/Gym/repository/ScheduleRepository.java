package com.example.Gym.repository;

import com.example.Gym.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {
    Set<Schedule> getAllByPrice(Integer cena);
    Schedule findOneByBeginDate(LocalDate datum);
    Set<Schedule> getAllBy();
    Set<Schedule> getAllByBeginDate(LocalDate datum);

    Schedule findOneByPrice(Integer cena);

    Schedule findOneById(Integer id);
}
