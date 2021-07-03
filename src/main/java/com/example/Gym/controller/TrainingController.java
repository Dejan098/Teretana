package com.example.Gym.controller;

import com.example.Gym.model.*;
import com.example.Gym.model.DTO.*;
import com.example.Gym.repository.OcenaRepository;
import com.example.Gym.repository.TrainingRepository;
import com.example.Gym.service.ScheduleService;
import com.example.Gym.service.TrainingService;
import com.example.Gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value="/training")
public class TrainingController {
    private ScheduleService scheduleService;

    private TrainingRepository trainingRepository;

    @Autowired
    public void setTrainingRepository(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    private OcenaRepository ocenaRepository;

    @Autowired
    public void setOcenaRepository(OcenaRepository ocenaRepository) {
        this.ocenaRepository = ocenaRepository;
    }

    @Autowired
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }




   private TrainingService trainingService;
   @Autowired
   public void setTrainingService(TrainingService trainingService) {
        this.trainingService = trainingService;
    }


    @GetMapping(value="/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Training>> getallTrainings(){

        Set<Training> treninzi = trainingService.getalltrainings();
        return new ResponseEntity<>(treninzi, HttpStatus.OK);
    }


    @PostMapping(
            value = "/search",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Training>> pretraga(@RequestBody StringDTO stringdto) throws Exception{
       String kriterijum=stringdto.getKriterijum();

       if(kriterijum.equals("naziv")) {
           Set<Training> treninzi = trainingService.findallbynaziv(stringdto.getNaziv());
           return new ResponseEntity<>(treninzi, HttpStatus.OK);
       }
       else if (kriterijum.equals("tip")){
           Set<Training> treninzi = trainingService.findallbytip(stringdto.getTip());
           return new ResponseEntity<>(treninzi, HttpStatus.OK);
       }
       else if(kriterijum.equals("opis")){
           Set<Training> treninzi = trainingService.findallbyopis(stringdto.getOpis());
           return new ResponseEntity<>(treninzi, HttpStatus.OK);
       }

       if(kriterijum.equals("cena")){
           Set<Schedule> termini=scheduleService.findallbycena(stringdto.getCena());
           Set<Training> treninzi = new HashSet<>();
           for(Schedule termintrening:termini){
               Training trening = trainingService.findallbytermin(termintrening);
               treninzi.add(trening);
           }
           return new ResponseEntity<>(treninzi, HttpStatus.OK);
       }
       if(kriterijum.equals("vreme")){
           Set<Schedule> termini=scheduleService.findallbyvreme(stringdto.getVreme());
           Set<Training> treninzi = new HashSet<>();
         for(Schedule termintrening:termini){
             Training trening = trainingService.findallbytermin(termintrening);
             treninzi.add(trening);
         }

         return new ResponseEntity<>(treninzi, HttpStatus.OK);
       }
       return null;
    }

    @GetMapping(value="/donetrainings", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Set<Training>> donetrainings(){
        Member user = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Schedule> termini = user.getSchedule();
        LocalDate trenutno_vreme=LocalDate.now();
        Set<Training> treninzi = new HashSet<>();
        for(Schedule schedule:termini){
            if(schedule.getBeginDate().isBefore(trenutno_vreme)){
               treninzi.add(schedule.getTraining());

            }
        }




        return new ResponseEntity(treninzi, HttpStatus.OK);
    }

    @GetMapping(value="/donetrainingsocenjeni", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Set<Trainingdtoprikaz>> donetrainingsocenjeni(){
        Member user = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Schedule> termini = user.getSchedule();
        LocalDate trenutno_vreme=LocalDate.now();
        Set<Training> treninzi = new HashSet<>();
        for(Schedule schedule:termini){
            if(schedule.getBeginDate().isBefore(trenutno_vreme)){
                treninzi.add(schedule.getTraining());

            }
        }

        Set<Training> treninzidtos =new HashSet<>();

        for(Training training:treninzi){
            if(training.getOcena()!=null){
                treninzidtos.add(training);
            }
        }

        Set<Trainingdtoprikaz> treninziprikaz=new HashSet<>();

        for(Training treningic:treninzidtos){
            Trainingdtoprikaz prikaz=new Trainingdtoprikaz(treningic.getId(), treningic.getName(), treningic.getDescription(),treningic.getType(), treningic.getDuration(),treningic.getOcena().getOcena());
            treninziprikaz.add(prikaz);
        }




        return new ResponseEntity(treninziprikaz, HttpStatus.OK);
    }



    @GetMapping(value="/donetrainingsnoocena", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Set<Training>> donetrainingsnoocena(){
        Member user = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Schedule> termini = user.getSchedule();
        LocalDate trenutno_vreme=LocalDate.now();
        Set<Training> treninzi = new HashSet<>();
        for(Schedule schedule:termini){
            if(schedule.getBeginDate().isBefore(trenutno_vreme)){
                treninzi.add(schedule.getTraining());

            }
        }

        Set<Training> treninzidtos =new HashSet<>();

        for(Training training:treninzi){
            if(training.getOcena()==null){
                treninzidtos.add(training);
            }
        }


        return new ResponseEntity(treninzidtos, HttpStatus.OK);
    }


    @PostMapping(value="/ocenitrenings", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Training> Ocenitrening(@RequestBody Trainingdto idDto) throws Exception {
        Training trening=trainingService.findOneById(idDto.getId());
        Ocena ocena=new Ocena();
        ocena.setOcena(idDto.getOcena());
        ocenaRepository.save(ocena);
        trening.setOcena(ocena);
        trainingRepository.save(trening);





        return new ResponseEntity(trening, HttpStatus.OK);
    }



}
