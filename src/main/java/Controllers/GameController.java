package Controllers;

import Models.Roll;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Game")
public class GameController {

    @GetMapping("/Roll")
    public ResponseEntity<Roll> Roll(@RequestBody Roll prevRoll){
        try{
            Roll next = new Roll(prevRoll.getRollBase());
            return new ResponseEntity<>(next, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
