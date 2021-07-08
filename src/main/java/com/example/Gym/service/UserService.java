package com.example.Gym.service;

import com.example.Gym.model.*;
import com.example.Gym.model.DTO.UserDTO;
import com.example.Gym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authService;

    public User findById(Integer id) throws AccessDeniedException {
        User u = userRepository.findById(id).orElseGet(null);
        return u;
    }

    public List<User> findAll() throws AccessDeniedException {
        List<User> result = userRepository.findAll();
        return result;
    }

    public User save(UserRequest userRequest) {
        Member u = new Member();
        u.setKorisnickoime((u.getKorisnickoime()));
        u.setKorisnickoime(userRequest.getUsername());
        u.setSurname(userRequest.getSurname());
        u.setNamee(userRequest.getNamee());
        u.setEmail(userRequest.getEmail());
        u.setRola("member");
        u.setActive(true);
        u.setBirthDate(userRequest.getBirthDate());
        u.setPhoneNumber(userRequest.getPhoneNumber());
        // pre nego sto postavimo lozinku u atribut hesiramo je
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setEnabled(true);

        List<Authority> auth = authService.findByname("ROLE_USER");
        // u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
        u.setAuthorities(auth);

        u = this.userRepository.save(u);
        return u;
    }

    public User saveTrener(UserRequest userRequest) {
        Trainer u = new Trainer();
        u.setKorisnickoime((u.getKorisnickoime()));
        u.setKorisnickoime(userRequest.getUsername());
        u.setSurname(userRequest.getSurname());
        u.setNamee(userRequest.getNamee());
        u.setEmail(userRequest.getEmail());
        u.setRola("trainer");
        u.setActive(true);
        u.setBirthDate(userRequest.getBirthDate());
        u.setPhoneNumber(userRequest.getPhoneNumber());
        // pre nego sto postavimo lozinku u atribut hesiramo je
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setEnabled(false);

        List<Authority> auth = authService.findByname("ROLE_TRAINER");
        // u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
        u.setAuthorities(auth);

        u = this.userRepository.save(u);
        return u;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
