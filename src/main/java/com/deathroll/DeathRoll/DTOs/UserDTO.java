package com.deathroll.DeathRoll.DTOs;

import java.time.LocalDate;
import java.util.List;

public record UserDTO (
        Long id,
        String username,
        double gold,
        double dollars,
        LocalDate createdAt,
        List<RollDTO> rolls
){}
