package com.deathroll.DeathRoll.Models;

import com.deathroll.DeathRoll.DTOs.RollChainDTO;
import com.deathroll.DeathRoll.DTOs.RollDTO;
import com.deathroll.DeathRoll.DTOs.UserDTO;

import java.util.ArrayList;
import java.util.List;

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

    public static RollChainDTO toRollChainDTO(RollChain rollChain){
        return new RollChainDTO(
                rollChain.getId(),
                rollChain.getRolls().stream().map(EntitiesMapper::toRollDTO).toList()
        );
    }

    public static List<RollDTO> toRollDTOList(List<Roll> rolls){
        ArrayList<RollDTO> dtos = new ArrayList<>();

        for (Roll roll : rolls){
            dtos.add(toRollDTO(roll));
        }

        return dtos;
    }

    public static List<UserDTO> toUserDTOList(List<User> users){
        ArrayList<UserDTO> dtos = new ArrayList<>();

        for (User user : users){
            dtos.add(toUserDTO(user));
        }

        return dtos;
    }

    public static List<RollChainDTO> toRollChainDTOList(List<RollChain> rollChains){
        ArrayList<RollChainDTO> dtos = new ArrayList<>();

        for (RollChain rollChain : rollChains){
            dtos.add(toRollChainDTO(rollChain));
        }

        return dtos;
    }
}
