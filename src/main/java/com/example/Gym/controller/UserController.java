package com.example.Gym.controller;

import com.example.Gym.model.DTO.UserDTO;
import com.example.Gym.model.DTO.UserLogin;
import com.example.Gym.model.User;
import com.example.Gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/users")
public class UserController {

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> login(@RequestBody UserLogin userlogindto) throws Exception{

        User user=userService.login(userlogindto.getEmail(),userlogindto.getPassword());

        UserDTO logovanikorisnik = new UserDTO();

        if(user!=null){
            logovanikorisnik = new UserDTO(user.getId(),user.getUsername(),user.getPassword(),user.getNamee(),user.getSurname(),user.getPhoneNumber(),user.getEmail(),user.getBirthDate(),user.getRola(),user.getActive());
            return new ResponseEntity<>(logovanikorisnik,HttpStatus.OK);
        }
        return new ResponseEntity<>(logovanikorisnik,HttpStatus.BAD_REQUEST);
    }
}
