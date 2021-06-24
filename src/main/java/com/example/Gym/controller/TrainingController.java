package com.example.Gym.controller;

import com.example.Gym.model.DTO.StringDTO;
import com.example.Gym.model.DTO.UserDTO;
import com.example.Gym.model.DTO.UserLogin;
import com.example.Gym.model.Training;
import com.example.Gym.model.User;
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
       String kriterijum=stringdto.getKriterijumpretrage();
       if(kriterijum=="naziv") {
           Set<Training> treninzi = trainingService.findallbynaziv(stringdto.getVrednostPretrage());
           return new ResponseEntity<>(treninzi, HttpStatus.OK);
       }
       else if (kriterijum=="naziv"){
           Set<Training> treninzi = trainingService.findallbytip(stringdto.getVrednostPretrage());
           return new ResponseEntity<>(treninzi, HttpStatus.OK);
       }
       else if(kriterijum=="opisu"){
           Set<Training> treninzi = trainingService.findallbyopis(stringdto.getVrednostPretrage());
           return new ResponseEntity<>(treninzi, HttpStatus.OK);
       }

       return null;
    }

}
