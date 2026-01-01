package com.deathroll.DeathRoll.Models;

import com.deathroll.DeathRoll.DTOs.RollDTO;
import com.deathroll.DeathRoll.DTOs.UserDTO;

// Mapper of Entities into their respective DTOs for sending to client
public class EntitiesMapper {

    public static RollDTO toRollDTO(Roll roll){
        return new RollDTO(
                roll.getId(),
                roll.getUser() != null ? roll.getUser().getUsername() : "System",
                roll.getRollBase(),
                roll.getRolledNumber());
    }

    public static UserDTO toUserDTO(User user){
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getGold(),
                user.getDollars(),
                user.getCreatedAt(),
                user.getRolls().stream().map(EntitiesMapper::toRollDTO).toList()
        );
    }
}
