package com.example.Gym.service;

import com.example.Gym.model.Member;
import com.example.Gym.model.Schedule;
import com.example.Gym.model.Training;
import com.example.Gym.model.User;
import com.example.Gym.repository.TrainerRepository;
import com.example.Gym.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TrainingService {
    private TrainingRepository trainingRepository;

    @Autowired
    public void setTrainingRepository(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public Set<Training> getalltrainings() {return trainingRepository.findAllBy();
    }

    public Set<Training> findallbynaziv(String name) { return trainingRepository.getAllByName(name);
    }

    public Set<Training> findallbytip(String type) { return trainingRepository.getAllByType(type);
    }

    public Set<Training> findallbyopis(String opis) { return trainingRepository.getAllByDescription(opis);
    }

    public Training findallbytermin(Schedule schedule) {
        return trainingRepository.findOneBySchedule(schedule);
    }

    public Training findOneById(Integer id) { return trainingRepository.findOneById(id);
    }
}
