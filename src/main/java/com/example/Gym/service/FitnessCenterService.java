package com.example.Gym.service;

import com.example.Gym.repository.FitnessCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FitnessCenterService {
    private FitnessCenterRepository fitnessRepository;

    @Autowired
    public void setFitnessRepository(FitnessCenterRepository fitnessRepository) {
        this.fitnessRepository = fitnessRepository;
    }
}
