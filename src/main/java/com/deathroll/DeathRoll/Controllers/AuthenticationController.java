package com.deathroll.DeathRoll.Controllers;

import com.deathroll.DeathRoll.DTOs.MessageResponse;
import com.deathroll.DeathRoll.Models.User;
import com.deathroll.DeathRoll.Repositories.UserRepository;
import com.deathroll.DeathRoll.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @PostMapping("/Login")
    public ResponseEntity<MessageResponse> Login(@RequestBody User userToLogin) {
        String token = authenticationService.login(userToLogin);
        return ResponseEntity.ok(new MessageResponse(token));
    }

    @PostMapping("/Register")
    public ResponseEntity<MessageResponse> Register(@RequestBody User userToRegister) {
        String token = authenticationService.register(userToRegister);
        return ResponseEntity.ok(new MessageResponse(token));
    }

    @PostMapping("/CheckUsernameViability")
    public ResponseEntity<MessageResponse> CheckUsernameViability(@RequestBody User userWithUsernameToCheck) {
        boolean userExists = userRepository.existsByUsername(userWithUsernameToCheck.getUsername());
        if(userExists){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse("User with this username already exists"));
        }
        return ResponseEntity.ok(new MessageResponse("Username is available"));
    }

    @PostMapping("CheckEmailViability")
    public ResponseEntity<MessageResponse> CheckEmailViability(@RequestBody User userWithEmailToCheck) {
        boolean userExists = userRepository.existsByEmail(userWithEmailToCheck.getEmail());
        if(userExists){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse("User with such an email already exists"));
        }
        return ResponseEntity.ok(new MessageResponse("Email is available"));
    }
}