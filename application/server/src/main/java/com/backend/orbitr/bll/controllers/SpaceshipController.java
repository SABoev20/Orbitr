package com.backend.orbitr.bll.controllers;

import com.backend.orbitr.bll.services.AuthService;
import com.backend.orbitr.bll.services.SpaceshipService;
import com.backend.orbitr.dal.models.Spaceship;
import com.backend.orbitr.dal.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spaceships")
public class SpaceshipController {
    private final SpaceshipService spaceshipService;
    private final AuthService authService;

    @Autowired
    public SpaceshipController(SpaceshipService spaceshipService, AuthService authService){
        this.spaceshipService = spaceshipService;
        this.authService = authService;
    }

    @PostMapping("/create")
    public Spaceship createSpaceship(@RequestBody Spaceship spaceship, Authentication authentication) {
        User user = authService.getUserByToken(authentication);
        spaceship.setUser(user);

        return spaceshipService.saveSpaceship(spaceship);
    }

    @GetMapping(value = "/inventory")
    public List<Spaceship> getUserInventory(Authentication authentication) {
        User user = authService.getUserByToken(authentication);
        return spaceshipService.getSpaceshipsByUser(user);
    }

    @GetMapping("/collected")
    public String getUserSpaceshipsCollected(Authentication authentication) {
        User user = authService.getUserByToken(authentication);
        return spaceshipService.getSpaceshipsCollected(user);
    }
}