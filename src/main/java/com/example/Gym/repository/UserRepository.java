package com.example.Gym.repository;

import com.example.Gym.model.DTO.UserDTO;
import com.example.Gym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {


    User findByEmailAndPassword(String email,String password);

}
