package com.example.Gym.controller;

import com.example.Gym.model.User;
import com.example.Gym.model.UserRequest;
import com.example.Gym.model.UserTokenRoleDto;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

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


}
