package com.deathroll.DeathRoll.Controllers;

import com.deathroll.DeathRoll.Models.User;
import com.deathroll.DeathRoll.Repositories.UserRepository;
import com.deathroll.DeathRoll.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @PostMapping("/Login")
    public ResponseEntity<String> Login(@RequestBody User userToLogin) {
        String token = authenticationService.login(userToLogin);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/Register")
    public ResponseEntity<String> Register(@RequestBody User userToRegister) {
        String token = authenticationService.register(userToRegister);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/CheckUsernameViability")
    public ResponseEntity<String> CheckUsernameViability(@RequestBody User userWithUsernameToCheck) {
        boolean userExists = userRepository.existsByUsername(userWithUsernameToCheck.getUsername());
        if(userExists){
            return new ResponseEntity<String>("User with such a name already exists in the system", HttpStatus.CONFLICT);
        }
        else{
            return ResponseEntity.ok("Username available");
        }
    }


    @PostMapping("CheckEmailViability")
    public ResponseEntity<String> CheckEmailViability(@RequestBody User userWithEmailToCheck) {
        boolean userExists = userRepository.existsByEmail(userWithEmailToCheck.getEmail());
        if(userExists){
            return new ResponseEntity<String>("User with such an email already exists in the system", HttpStatus.CONFLICT);
        }
        else{
            return ResponseEntity.ok("Email available");
        }
    }
}