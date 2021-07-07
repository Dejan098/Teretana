package com.example.Gym.controller;

import com.example.Gym.model.DTO.FitnessCenterDTO;
import com.example.Gym.model.DTO.IdDto;
import com.example.Gym.model.DTO.ScheduleDTO;
import com.example.Gym.model.FitnessCenter;
import com.example.Gym.model.Hall;
import com.example.Gym.model.Schedule;
import com.example.Gym.model.Trainer;
import com.example.Gym.repository.FitnessCenterRepository;
import com.example.Gym.service.FitnessCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value="/fitnesscenter")
public class FitnessCenterController {

    private FitnessCenterService fitnessService;
    private FitnessCenterRepository fitnessCenterRepository;

    @Autowired
    public void setFitnessCenterRepository(FitnessCenterRepository fitnessCenterRepository) {
        this.fitnessCenterRepository = fitnessCenterRepository;
    }

    @Autowired
    public void setFitnessService(FitnessCenterService fitnessService) {
        this.fitnessService = fitnessService;
    }

    @GetMapping(value="/getfitnescenter", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<FitnessCenter>> getfitnescentress(){

        Set<FitnessCenter> centri =fitnessCenterRepository.findAllBy();




        return new ResponseEntity(centri, HttpStatus.OK);
    }

    @PostMapping(value="/creatfitnescenter", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FitnessCenter> Createschedule(@RequestBody FitnessCenterDTO idDto) throws Exception {

        FitnessCenter centar=new FitnessCenter();
        centar.setAddress(idDto.getAddress());
        centar.setEmail(idDto.getEmail());
        centar.setName(idDto.getName());
        centar.setPhone(idDto.getPhone());
        fitnessCenterRepository.save(centar);

        return new ResponseEntity(centar, HttpStatus.OK);
    }


    @PostMapping(value="/izmenicentar", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FitnessCenter> IzmeniCentar(@RequestBody FitnessCenterDTO idDto) throws Exception {

        FitnessCenter centar=fitnessCenterRepository.findOneById(idDto.getId());
        centar.setAddress(idDto.getAddress());
        centar.setEmail(idDto.getEmail());
        centar.setName(idDto.getName());
        centar.setPhone(idDto.getPhone());
        fitnessCenterRepository.save(centar);

        return new ResponseEntity(centar, HttpStatus.OK);
    }


    @PostMapping(value="/deletefitnescenter", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FitnessCenter> DeleteCentar(@RequestBody IdDto idDto) throws Exception {

        FitnessCenter centar=fitnessCenterRepository.findOneById(idDto.getId());
        fitnessCenterRepository.delete(centar);

        return new ResponseEntity(centar, HttpStatus.OK);
    }

}
