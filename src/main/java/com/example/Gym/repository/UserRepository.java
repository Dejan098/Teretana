package com.example.Gym.repository;

import com.example.Gym.model.DTO.UserDTO;
import com.example.Gym.model.Trainer;
import com.example.Gym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User,Integer> {


    User findByEmailAndPassword(String email,String password);
    User findByEmail(String email);

    Set<User> findAllBy();

    Optional<User> findById(Integer id);

    User findOneById(Integer id);
}
