package Services;

import Models.User;
import Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(User user) {
        user.setHashedPassword(passwordEncoder.encode(user.getHashedPassword()));
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
