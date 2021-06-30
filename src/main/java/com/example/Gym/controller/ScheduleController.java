package com.example.Gym.controller;

import com.example.Gym.model.Schedule;
import com.example.Gym.model.Training;
import com.example.Gym.repository.ScheduleRepository;
import com.example.Gym.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value="/shcedule")
public class ScheduleController {

    private ScheduleRepository scheduleRepository;
    private ScheduleService scheduleService;

    @Autowired
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Autowired
    public void setScheduleRepository(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

        @GetMapping(value="/schedules", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Set<Schedule>> getallschedules(){

            Set<Schedule> termini = scheduleService.getallschedules();
            return new ResponseEntity<>(termini, HttpStatus.OK);
        }
}
