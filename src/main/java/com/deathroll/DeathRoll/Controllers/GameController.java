package com.deathroll.DeathRoll.Controllers;

import com.deathroll.DeathRoll.Models.Roll;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@RequestMapping("/Game")
public class GameController {

    @GetMapping("/Roll")
    public ResponseEntity<Roll> Roll(
            @RequestBody Roll prevRoll,
            Principal principal //Principal is automatically injected via Spring Principal resolver
    ){
        try{
            Roll next = new Roll(prevRoll.getRollBase());
            return new ResponseEntity<>(next, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
