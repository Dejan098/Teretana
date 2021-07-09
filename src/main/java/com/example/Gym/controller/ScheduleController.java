package com.example.Gym.controller;

import com.example.Gym.model.*;
import com.example.Gym.model.DTO.IdDto;
import com.example.Gym.model.DTO.ScheduleDTO;
import com.example.Gym.repository.HallRepository;
import com.example.Gym.repository.ScheduleRepository;
import com.example.Gym.repository.TrainingRepository;
import com.example.Gym.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping(value="/shcedule")
public class ScheduleController {

    private ScheduleRepository scheduleRepository;
    private ScheduleService scheduleService;
    private HallRepository hallRepository;
    private TrainingRepository trainingRepository;

    @Autowired
    public void setTrainingRepository(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Autowired
    public void setHallRepository(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

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

    @GetMapping(value="/sortbyvreme",produces = MediaType.APPLICATION_JSON_VALUE)                                           // value nije naveden, jer koristimo bazni url
    public ResponseEntity<List<Schedule>> sortscheduleByDate() {
        Set<Schedule> termini = scheduleService.getallschedules();


        // Kreiramo listu DTO objekata
        List<Schedule> terminidtos = new ArrayList<>();
        List<LocalDate> lista_datuma =new ArrayList<>();

        for (Schedule schedule : termini) {
            lista_datuma.add(schedule.getBeginDate());

        }
        java.util.Collections.sort(lista_datuma);
        for(LocalDate naz: lista_datuma){
            Schedule schedule=scheduleRepository.findOneByBeginDate(naz);
            terminidtos.add(schedule);
        }


        return new ResponseEntity<>(terminidtos, HttpStatus.OK);
    }

    @GetMapping(value="/schedulesoftrainer", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<Set<Schedule>> gettrainerschedules(){

        Trainer user = (Trainer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        FitnessCenter centar =user.getFitnescentar();



        return new ResponseEntity(centar.getSchedulers(), HttpStatus.OK);
    }

    @GetMapping(value="/sortbycena",produces = MediaType.APPLICATION_JSON_VALUE)                                           // value nije naveden, jer koristimo bazni url
    public ResponseEntity<List<Schedule>> sortscheduleByCena() {
        Set<Schedule> termini = scheduleService.getallschedules();


        // Kreiramo listu DTO objekata
        List<Schedule> terminidtos = new ArrayList<>();
        List<Integer> lista_datuma =new ArrayList<>();

        for (Schedule schedule : termini) {
            lista_datuma.add(schedule.getPrice());

        }
        java.util.Collections.sort(lista_datuma);
        for(Integer naz: lista_datuma){
            Schedule schedule=scheduleRepository.findOneByPrice(naz);
            terminidtos.add(schedule);
        }


        return new ResponseEntity<>(terminidtos, HttpStatus.OK);
    }

    @PostMapping(value="/createschedule", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<Schedule> Createschedule(@RequestBody ScheduleDTO idDto) throws Exception {
        Trainer user = (Trainer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Hall sala=hallRepository.findOneById(idDto.getSala());
        Set<Schedule> termini_u_sali=sala.getScheduleset();
        for(Schedule termin:termini_u_sali){
            if(termin.getBeginDate().equals(idDto.getBeginDate())){
                throw new Exception("postoji kreiran termin za to vreme u toj sali");
            }
        }
        Schedule schedule=new Schedule();
        schedule.setBeginDate(idDto.getBeginDate());
        schedule.setPrice(idDto.getPrice());
        schedule.setFitnesraspored(user.getFitnescentar());
        schedule.setTraining(trainingRepository.findOneById(idDto.getTraining()));


        schedule.setSlobodnih_mesta(sala.getCapacity());
        Set<Hall>lista_sala=new HashSet<Hall>();
        lista_sala.add(sala);
        schedule.setHalls(lista_sala);
        scheduleRepository.save(schedule);




        return new ResponseEntity(schedule, HttpStatus.OK);
    }

    @PostMapping(value="/reserveschedule", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Schedule> Reserveappointment(@RequestBody IdDto idDto) throws Exception {
        Member user = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Schedule schedule=scheduleService.findById(idDto.getId());
        schedule.setSlobodnih_mesta(schedule.getSlobodnih_mesta()-1);
        schedule.setPrijavljenih(schedule.getPrijavljenih()+1);
        Set<Member> members=new HashSet<>();
        members.add(user);
        schedule.setMember(members);
        scheduleService.save(schedule);



        return new ResponseEntity(schedule, HttpStatus.OK);
    }



    @GetMapping(value="/getreserveschedule", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Set<Schedule>> reserveschedulees(){
        Set<Schedule> termini = scheduleService.getallschedules();
        Member user = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Set<Schedule> terminidtos = user.getSchedule();



        return new ResponseEntity(terminidtos, HttpStatus.OK);
    }
    @PostMapping(value="/cancelappointment", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Schedule> Cancelppointment(@RequestBody IdDto idDto) throws Exception {
        Schedule appointment=scheduleService.findById(idDto.getId());
        LocalDate trenutno_vreme=LocalDate.now();
        LocalDate date1=appointment.getBeginDate();

        if(trenutno_vreme.isAfter(date1)){

            throw new Exception("Nije moguce otkazivanje proslo je 24h");
        }

        else {
            appointment.setMember(null);
            appointment.setSlobodnih_mesta(appointment.getSlobodnih_mesta()+1);
            appointment.setPrijavljenih(appointment.getPrijavljenih()-1);
            scheduleRepository.save(appointment);

        }

        return new ResponseEntity(appointment, HttpStatus.OK);
    }


    @PostMapping(value="/deleteschedule", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<Schedule> DeleteSchedule(@RequestBody IdDto idDto) throws Exception {
        Schedule appointment=scheduleService.findById(idDto.getId());
        scheduleRepository.delete(appointment);

        return new ResponseEntity(appointment, HttpStatus.OK);
    }


    @PostMapping(value="/izmenieschedule", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<Schedule> IzmeniSchedule(@RequestBody ScheduleDTO idDto) throws Exception {
        Schedule schedule=scheduleRepository.findOneById(idDto.getId());
        Hall sala=hallRepository.findOneById(idDto.getSala());
        Set<Schedule> termini_u_sali=sala.getScheduleset();
        for(Schedule termin:termini_u_sali){
            if(termin.getBeginDate().equals(idDto.getBeginDate())){
                throw new Exception("postoji kreiran termin za to vreme u toj sali");
            }
        }
        schedule.setBeginDate(idDto.getBeginDate());
        schedule.setPrice(idDto.getPrice());
        schedule.setTraining(trainingRepository.findOneById(idDto.getTraining()));


        schedule.setSlobodnih_mesta(sala.getCapacity());
        Set<Hall>lista_sala=new HashSet<Hall>();
        lista_sala.add(sala);
        schedule.setHalls(lista_sala);
        scheduleRepository.save(schedule);




        return new ResponseEntity(schedule, HttpStatus.OK);
    }
}
