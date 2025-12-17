package Controllers;

import Models.User;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Auth")
public class AuthenticationController {

    @PostMapping("/Login")
    public void Login(@RequestBody User userToLogin){
        //TODO procedura logowania
    }

    @PostMapping("/Register")
    public void Register(@RequestBody User userToRegister){
        //TODO procedura rejestracji
    }
}
