package com.example.Gym.controller;

import com.example.Gym.model.DTO.StringDTO;
import com.example.Gym.model.DTO.UserDTO;
import com.example.Gym.model.DTO.UserLogin;
import com.example.Gym.model.Schedule;
import com.example.Gym.model.Training;
import com.example.Gym.model.User;
import com.example.Gym.service.ScheduleService;
import com.example.Gym.service.TrainingService;
import com.example.Gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value="/training")
public class TrainingController {
    private ScheduleService scheduleService;
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

}
