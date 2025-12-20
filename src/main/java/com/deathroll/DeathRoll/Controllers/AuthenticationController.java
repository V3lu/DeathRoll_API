package com.deathroll.DeathRoll.Controllers;

import com.deathroll.DeathRoll.Models.User;
import com.deathroll.DeathRoll.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/Login")
    public ResponseEntity<String> Login(@RequestBody User userToLogin){
        String token = authenticationService.login(userToLogin);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/Register")
    public ResponseEntity<String> Register(@RequestBody User userToRegister){
        String token = authenticationService.register(userToRegister);
        return ResponseEntity.ok(token);
    }
}
