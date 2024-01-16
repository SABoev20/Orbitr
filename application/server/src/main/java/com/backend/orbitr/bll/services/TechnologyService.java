package com.backend.orbitr.bll.services;

import com.backend.orbitr.dal.models.Technology;
import com.backend.orbitr.dal.repositories.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyService {
    private final TechnologyRepository technologyRepository;

    @Autowired
    public TechnologyService(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    public Technology saveTechnology(Technology technology){
        return technologyRepository.save(technology);
    }
    public List<Technology> getAllTechnologies() {
        return technologyRepository.findAll();
    }
}
