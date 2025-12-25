package com.deathroll.DeathRoll.Controllers;

import com.deathroll.DeathRoll.DTOs.RollResponse;
import com.deathroll.DeathRoll.Models.Roll;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/Game")
@CrossOrigin(origins = "http://localhost:4200")
public class GameController {

    @PostMapping("/PlaceRoll")
    public ResponseEntity<RollResponse> Roll(
            @RequestBody Roll prevRoll,
            Principal principal //Principal is automatically injected via Spring Principal resolver
    ){
        try{
            Roll next = new Roll(prevRoll.getRollBase());
            return ResponseEntity.ok(new RollResponse(List.of(next)));
            // TODO save rolls in db
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
