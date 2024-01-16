package com.backend.orbitr.bll.controllers;

import com.backend.orbitr.bll.services.AuthService;
import com.backend.orbitr.bll.services.OfferService;
import com.backend.orbitr.dal.models.Offer;
import com.backend.orbitr.dal.models.Spaceship;
import com.backend.orbitr.dal.models.Transaction;
import com.backend.orbitr.dal.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("offers")
public class OfferController {
    private final AuthService authService;
    private final OfferService offerService;

    @Autowired
    public OfferController(AuthService authService, OfferService offerService){
        this.authService = authService;
        this.offerService = offerService;
    }

    @GetMapping("/available")
    public List<Offer> getAvailableOffers(Authentication authentication) {
        User user = authService.getUserByToken(authentication);

        return offerService.getAvailableOffers(user);
    }

    @PostMapping("/purchase")
    public void purchaseOffer(@RequestBody Offer offer, Authentication authentication) {
        User user = authService.getUserByToken(authentication);
        offerService.purchaseOffer(user, offer);
    }

    @PostMapping("/create")
    public Offer createOffer(@RequestBody Offer offer, Authentication authentication) {
        User user = authService.getUserByToken(authentication);
        offer.setUser(user);

        return offerService.saveOffer(offer);
    }
}
