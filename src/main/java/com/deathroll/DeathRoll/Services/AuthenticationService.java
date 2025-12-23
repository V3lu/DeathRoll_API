package com.deathroll.DeathRoll.Services;

import com.deathroll.DeathRoll.Models.User;
import com.deathroll.DeathRoll.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(User user) {
        user.setHashedPassword(passwordEncoder.encode(user.getHashedPassword()));
        user.setCreatedAt(LocalDate.now());
        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public String login(User user) {
        User dbUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(user.getHashedPassword(), dbUser.getHashedPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtService.generateToken(dbUser);
    }
}
