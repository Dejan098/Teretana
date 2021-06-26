package com.example.Gym.service;

import com.example.Gym.model.Authority;

import java.util.List;


public interface AuthorityService {
    List<Authority> findById(Integer id);
    List<Authority> findByname(String name);
}
