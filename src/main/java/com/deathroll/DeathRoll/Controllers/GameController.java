package com.deathroll.DeathRoll.Controllers;

import com.deathroll.DeathRoll.DTOs.RollDTO;
import com.deathroll.DeathRoll.Models.EntitiesMapper;
import com.deathroll.DeathRoll.Models.Roll;
import com.deathroll.DeathRoll.Models.User;
import com.deathroll.DeathRoll.Repositories.RollRepository;
import com.deathroll.DeathRoll.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/Game")
public class GameController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RollRepository rollRepository;

    @PostMapping("/PlaceRoll")
    public ResponseEntity<RollDTO> Roll(
            @RequestBody Roll prevRoll,
            Authentication authentication //Injected automatically via spring resolver
    ){
        User user = (User) authentication.getPrincipal();
        Roll roll = new Roll(prevRoll.getRollBase());

        roll.setUser(user);
        rollRepository.save(roll);
        return ResponseEntity.ok(EntitiesMapper.toRollDTO(roll));
    }
}
