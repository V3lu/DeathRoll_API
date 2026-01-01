package com.deathroll.DeathRoll.Controllers;

import com.deathroll.DeathRoll.DTOs.RollDTO;
import com.deathroll.DeathRoll.DTOs.UserDTO;
import com.deathroll.DeathRoll.Models.EntitiesMapper;
import com.deathroll.DeathRoll.Models.Roll;
import com.deathroll.DeathRoll.Models.User;
import com.deathroll.DeathRoll.Repositories.RollRepository;
import com.deathroll.DeathRoll.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/Game")
public class GameController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RollRepository rollRepository;

    // Create new roll, save it in the db, map to RollDTO and send back to front-end
    @PostMapping("/PlaceRoll")
    public ResponseEntity<RollDTO> PlaceRoll(
            @RequestBody Roll prevRoll,
            Authentication authentication //Injected automatically via spring resolver
    ){
        User user = (User) authentication.getPrincipal();
        Roll roll = new Roll(prevRoll.getRollBase());

        roll.setUser(user);
        rollRepository.save(roll);
        return ResponseEntity.ok(EntitiesMapper.toRollDTO(roll));
    }

    // Search 5 opponents with active bets similar to chosen bet amount by the user
    @PostMapping("/SimilarBetOpponents")
    public ResponseEntity<List<UserDTO>> SimilarBetOpponents(
            @RequestBody Roll prevRoll
    ){
        //TODO choose a safe framework for generating SQL Queries or find other way to not write SQL by hand
    }
}
