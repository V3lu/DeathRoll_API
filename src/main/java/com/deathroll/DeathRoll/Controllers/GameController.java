package com.deathroll.DeathRoll.Controllers;

import com.deathroll.DeathRoll.DTOs.RollResponse;
import com.deathroll.DeathRoll.Models.Roll;
import com.deathroll.DeathRoll.Models.User;
import com.deathroll.DeathRoll.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/Game")
public class GameController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/PlaceRoll")
    public ResponseEntity<RollResponse> Roll(
            @RequestBody Roll prevRoll,
            Principal principal //Principal is automatically injected via Spring Principal resolver
    ){
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(
                () -> new RuntimeException("User not found")
        );
    }
}
