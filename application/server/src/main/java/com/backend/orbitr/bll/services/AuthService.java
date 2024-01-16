package com.backend.orbitr.bll.services;

import com.backend.orbitr.dal.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class AuthService {
    private final UserService userService;
    private final JwtEncoder encoder;
    //private JwtDecoder decoder;

    @Autowired
    public AuthService(UserService userService, JwtEncoder encoder){
        this.userService = userService;
        this.encoder = encoder;
    }

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean authenticate(String username, String password){
        Optional<User> optionalUser = Optional.ofNullable(userService.findByUsername(username));
        if(optionalUser.isEmpty()) return false;
        return passwordEncoder.matches(password, optionalUser.get().getPassword());
    }

    public String generateToken(String username) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(username)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public User getUserByToken(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        return userService.findByUsername(jwt.getClaim("sub"));
    }
}
