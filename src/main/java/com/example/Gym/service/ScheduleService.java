package com.example.Gym.service;

import com.example.Gym.model.Schedule;
import com.example.Gym.model.Training;
import com.example.Gym.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Service
public class ScheduleService {
    private ScheduleRepository scheduleRepository;

    @Autowired
    public void setScheduleRepository(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Set<Schedule> getallschedules() {return scheduleRepository.getAllBy();
    }

    public Set<Schedule> findallbycena(Integer cena) {
        return scheduleRepository.getAllByPrice(cena);
    }

    public Set<Schedule> findallbyvreme(LocalDate vremetermina) {
        return scheduleRepository.getAllByBeginDate(vremetermina);
    }

    public Schedule findById(Integer id) {return scheduleRepository.findOneById(id);}

    public Schedule save(Schedule appointment) throws Exception {
        Integer broj=appointment.getSlobodnih_mesta();
        if(broj<=0){
            throw new Exception("Nema slobodnih mesta!");
        }
        broj=broj-1;
        appointment.setSlobodnih_mesta(broj);
        return scheduleRepository.save(appointment);
    }
}
