package com.example.Gym.controller;

import com.example.Gym.model.*;
import com.example.Gym.model.DTO.IdDto;
import com.example.Gym.repository.ConfirmationTokenRepository;
import com.example.Gym.repository.UserRepository;
import com.example.Gym.security.TokenUtils;
import com.example.Gym.security.auth.JwtAuthenticationRequest;
import com.example.Gym.service.UserService;
import com.example.Gym.service.UserServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value="/auth", produces= MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private UserServiceDetails userDetailsService;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserTokenRoleDto> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
                                                                      HttpServletResponse response) {

        //Poziva se loadByUsername iz UserDetails i proverava da li postoji user sa ovakvim usernameom
        //Ako ne postoji, dobijamo badcredentials i vraca se 401 na klijentskoj strani
        //odbija se logovanje ako se za username vrati null
        //Ako korisnik postoji, password ce se automatski preko passwordEncodera hesirati po bcrypt jer je tako specif
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));

        // Ubaci korisnika u trenutni security kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token za tog korisnika
        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getEmail());
        int expiresIn = tokenUtils.getExpiredIn();

        String rola= user.getDecriminatorValue();
        Boolean enabled= user.isEnabled();

        // Vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(new UserTokenRoleDto(jwt,expiresIn,rola,enabled));
    }


    @PostMapping("/signup")
    public ResponseEntity<User> addUser(@RequestBody UserRequest userRequest, UriComponentsBuilder ucBuilder) throws ResourceConflictException {

        User existUser = this.userService.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getId(), "Username already exists");
        }

        User user = this.userService.save(userRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }


    @PostMapping("/signuptrener")
    public ResponseEntity<User> addtrener(@RequestBody UserRequest userRequest, UriComponentsBuilder ucBuilder) throws ResourceConflictException {

        User existUser = this.userService.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getId(), "Username already exists");
        }

        User user = this.userService.saveTrener(userRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }


    @PostMapping("/signuptrenerenabled")
    public ResponseEntity<User> addtrenerenabled(@RequestBody UserRequest userRequest, UriComponentsBuilder ucBuilder) throws ResourceConflictException {

        User existUser = this.userService.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getId(), "Username already exists");
        }

        User user = this.userService.saveTrener2(userRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @GetMapping(value="/gettreneri", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<User>> Gettreneri() {

        Set<User> useri = userRepository.findAllBy();
        Set<User> treneri=new HashSet<>();
        for(User user:useri){
            if(user.getDecriminatorValue().equals("trainer")){
                treneri.add(user);
            }
        }
        Set<User> treneri2 = new HashSet<>();
        for(User trener:treneri){
            if(trener.isEnabled()==true){
                treneri2.add(trener);
            }
        }
        return new ResponseEntity<>(treneri, HttpStatus.CREATED);
    }

    @GetMapping(value="/gettreneridisabled", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<User>> Gettreneridisabled() {

        Set<User> useri = userRepository.findAllBy();
        Set<User> treneri=new HashSet<>();
        for(User user:useri){
            if(user.getDecriminatorValue().equals("trainer")){
                treneri.add(user);
            }
        }
        Set<User> treneri2 = new HashSet<>();
        for(User trener:treneri){
            if(trener.isEnabled()==false){
                treneri2.add(trener);
            }
        }
        return new ResponseEntity<>(treneri2, HttpStatus.CREATED);
    }

    @PostMapping(value="/odobritrenera", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> OdobriTrenera(@RequestBody IdDto idDto) throws Exception {

        User user =userRepository.findOneById(idDto.getId());
        user.setEnabled(true);
        userRepository.save(user);





        return new ResponseEntity(user, HttpStatus.OK);
    }
    @PostMapping(value="/izbrisi", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> IzbrisiTrenera(@RequestBody IdDto idDto) throws Exception {

        User user =userRepository.findOneById(idDto.getId());
        userRepository.delete(user);





        return new ResponseEntity(user, HttpStatus.OK);
    }
}
