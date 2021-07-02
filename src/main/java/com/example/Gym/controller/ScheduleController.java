package com.example.Gym.controller;

import com.example.Gym.model.DTO.IdDto;
import com.example.Gym.model.Member;
import com.example.Gym.model.Schedule;
import com.example.Gym.repository.ScheduleRepository;
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
    public ResponseEntity<Set<Schedule>> sortscheduleByDate() {
        Set<Schedule> termini = scheduleService.getallschedules();


        // Kreiramo listu DTO objekata
        Set<Schedule> terminidtos = new HashSet<>();
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


    @GetMapping(value="/sortbycena",produces = MediaType.APPLICATION_JSON_VALUE)                                           // value nije naveden, jer koristimo bazni url
    public ResponseEntity<Set<Schedule>> sortscheduleByCena() {
        Set<Schedule> termini = scheduleService.getallschedules();


        // Kreiramo listu DTO objekata
        Set<Schedule> terminidtos = new HashSet<>();
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

    @PostMapping(value="/reserveschedule", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Schedule> Reserveappointment(@RequestBody IdDto idDto) throws Exception {
        Member user = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Schedule schedule=scheduleService.findById(idDto.getId());
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
            scheduleRepository.save(appointment);

        }

        return new ResponseEntity(appointment, HttpStatus.OK);
    }
}
