package com.example.Gym.service;

import com.example.Gym.model.Schedule;
import com.example.Gym.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class ScheduleService {
    private ScheduleRepository scheduleRepository;

    @Autowired
    public void setScheduleRepository(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Set<Schedule> findallbycena(Integer cena) {
        return scheduleRepository.getAllByPrice(cena);
    }

    public Set<Schedule> findallbyvreme(Date vremetermina) {
        return scheduleRepository.getAllByBeginDate(vremetermina);
    }
}
