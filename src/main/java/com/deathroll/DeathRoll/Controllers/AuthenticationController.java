package com.deathroll.DeathRoll.Controllers;

import com.deathroll.DeathRoll.DTOs.MessageResponse;
import com.deathroll.DeathRoll.DTOs.TokenUserResponse;
import com.deathroll.DeathRoll.DTOs.UserDTO;
import com.deathroll.DeathRoll.Models.EntitiesMapper;
import com.deathroll.DeathRoll.Models.User;
import com.deathroll.DeathRoll.Repositories.UserRepository;
import com.deathroll.DeathRoll.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.apache.coyote.Response;
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
    public ResponseEntity<TokenUserResponse> Login(@RequestBody User userToLogin) {
        String token = authenticationService.login(userToLogin);
        Optional<User> dbuser = userRepository.findByUsername(userToLogin.getUsername());
        return dbuser.map((user) -> ResponseEntity.ok(new TokenUserResponse(token, EntitiesMapper.toUserDTO(user)))).orElse(null);
    }

    @PostMapping("/Register")
    public ResponseEntity<TokenUserResponse> Register(@RequestBody User userToRegister) {
        String token = authenticationService.register(userToRegister);
        Optional<User> dbuser = userRepository.findByUsername(userToRegister.getUsername());
        return dbuser.map((user) -> ResponseEntity.ok(new TokenUserResponse(token, EntitiesMapper.toUserDTO(user)))).orElse(null);
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