package com.backend.orbitr.bll.controllers;

import com.backend.orbitr.bll.services.AuthService;
import com.backend.orbitr.bll.services.TechnologyService;
import com.backend.orbitr.dal.models.Spaceship;
import com.backend.orbitr.dal.models.Technology;
import com.backend.orbitr.dal.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("technologies")
public class TechnologyController {
    private final TechnologyService technologyService;
    private final AuthService authService;

    @Autowired
    public TechnologyController(TechnologyService technologyService, AuthService authService){
        this.technologyService = technologyService;
        this.authService = authService;
    }

    @GetMapping("/")
    public List<Technology> getAllTechnologies() {
        return technologyService.getAllTechnologies();
    }

    @PostMapping("/create")
    public Technology createTechnology(@RequestBody Technology technology) {
        return technologyService.saveTechnology(technology);
    }
}
