package com.deathroll.DeathRoll.Controllers;

import com.deathroll.DeathRoll.DTOs.MessageResponse;
import com.deathroll.DeathRoll.Models.User;
import com.deathroll.DeathRoll.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/InGameStatus")
public class InGameStatusController {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @PostMapping("/EnableInGameStatus")
    public ResponseEntity<MessageResponse> EnableInGameStatus(
            @RequestBody User user
    ){
        User dbuser = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        dbuser.setInGame(true);
        return ResponseEntity.ok(new MessageResponse("In-Game status set to true for user: " + user.getUsername()));
    }

    @Transactional  //Transaactional automatically calls repository.save()
    @PostMapping("/DisableInGameStatus")
    public ResponseEntity<MessageResponse> DisableInGameStatus(
            @RequestBody User user
    ){
        User dbuser = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        dbuser.setInGame(false);
        return ResponseEntity.ok(new MessageResponse("In-Game status set to false"));
    }
}
