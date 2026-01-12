package com.deathroll.DeathRoll.Controllers;

import com.deathroll.DeathRoll.DTOs.RollDTO;
import com.deathroll.DeathRoll.DTOs.UserDTO;
import com.deathroll.DeathRoll.Models.EntitiesMapper;
import com.deathroll.DeathRoll.Models.Roll;
import com.deathroll.DeathRoll.Models.RollChain;
import com.deathroll.DeathRoll.Models.User;
import com.deathroll.DeathRoll.Repositories.RollChainRepository;
import com.deathroll.DeathRoll.Repositories.RollRepository;
import com.deathroll.DeathRoll.Repositories.Specifications.RollChainSpecification;
import com.deathroll.DeathRoll.Repositories.Specifications.RollSpecification;
import com.deathroll.DeathRoll.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/Game")
public class GameController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RollRepository rollRepository;

    @Autowired
    private RollChainRepository rollChainRepository;

    // Create new roll, save it in the db, map to RollDTO and send back to front-end
    @Transactional
    @PostMapping("/PlaceRoll")
    public ResponseEntity<RollDTO> PlaceRoll(
            @RequestBody Roll prevRoll,
            Authentication authentication //Injected automatically via spring resolver
    ){
        User user = (User) authentication.getPrincipal();
        Roll roll = new Roll(prevRoll.getRollBase());

        User opponent = userRepository.findByUsername(prevRoll.getUser().getUsername()).orElseThrow(() -> new RuntimeException("Opponent not found"));

        // Roll chain for logged user
        if (user.isInGame()){
            Specification<RollChain> spec = Specification.where(RollChainSpecification.hasIsActiveStatus(true).and(RollChainSpecification.hasUserIdEqualTo(user.getId())));
            List<RollChain> currentRollChain = rollChainRepository.findAll(spec);
            currentRollChain.getFirst().getRolls().add(roll);
        }
        else{
            RollChain newRollChain = new RollChain();
            newRollChain.setActive(true);
            newRollChain.getRolls().add(roll);
        }


        // Roll chain for opponent user
        if (opponent.isInGame()){
            Specification<RollChain> spec = Specification.where(RollChainSpecification.hasIsActiveStatus(true).and(RollChainSpecification.hasUserIdEqualTo(opponent.getId())));
            List<RollChain> currentRollChain = rollChainRepository.findAll(spec);
            currentRollChain.getFirst().getRolls().add(roll);
        }
        else{
            RollChain newRollChain = new RollChain();
            newRollChain.setActive(true);
            newRollChain.getRolls().add(roll);
        }



        // 1. Recieve roll
        // 2. Check if it is the first roll of a chain for a user (comes down to checking if a user is in game currently)
        // 3. Create new RollChain if not in a game already or add to an existing one if in a game already
        roll.setUser(user);
        return ResponseEntity.ok(EntitiesMapper.toRollDTO(roll));
    }

    @PostMapping("/SimilarBetOpponents")
    public ResponseEntity<List<RollDTO>> SimilarBetOpponents(
            @RequestBody Roll prevRoll,
            Authentication authentication
    ){
        User loggedUser = (User) authentication.getPrincipal();
        List<Roll> rolls = findSimilarBets(prevRoll.getRollBase(), loggedUser);
        return ResponseEntity.ok(EntitiesMapper.toRollDTOList(rolls));
    }

    public List<Roll> findSimilarBets(
            int rollBase,
            User loggedUser
    ){
        if (rollBase != 0){
            Specification<Roll> spec = Specification
                    .where(RollSpecification.hasRollBaseBetweenBorder500(rollBase))
                    .and(RollSpecification.hasUserIdNotItsOwn(loggedUser.getId()));
            return rollRepository.findAll(spec);
        }
        else{
            return new ArrayList<>();
        }
    }

    @PostMapping("/GetOpponentData")
    public ResponseEntity<UserDTO> GetOpponentData(@RequestBody User user){
        User dbuser = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        UserDTO userToReturn = EntitiesMapper.toUserDTO(dbuser);
        return ResponseEntity.ok(userToReturn);
    }
}
