package com.backend.orbitr.bll.services;

import com.backend.orbitr.dal.models.Spaceship;
import com.backend.orbitr.dal.models.Technology;
import com.backend.orbitr.dal.models.User;
import com.backend.orbitr.dal.repositories.SpaceshipRepository;
import com.backend.orbitr.dal.repositories.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceshipService {

    private final SpaceshipRepository spaceshipRepository;
    private final TechnologyRepository technologyRepository;

    @Autowired
    public SpaceshipService(SpaceshipRepository spaceshipRepository, TechnologyRepository technologyRepository) {
        this.spaceshipRepository = spaceshipRepository;
        this.technologyRepository = technologyRepository;
    }

    public Spaceship saveSpaceship(Spaceship spaceship){
        Optional<Technology> technology = technologyRepository.findById(spaceship.getTechnologyId());
        technology.ifPresent(spaceship::setTechnology);
        return spaceshipRepository.save(spaceship);
    }

    public List<Spaceship> getSpaceshipsByUser(User user) {
        return spaceshipRepository.findByUser(user);
    }

    public String getSpaceshipsCollected(User user) {
        return String.valueOf(spaceshipRepository.findByUser(user).size());
    }
}
