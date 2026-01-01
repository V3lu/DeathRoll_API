package com.deathroll.DeathRoll.DTOs;

import java.time.LocalDate;
import java.util.List;

// Record exposes only necessary properties while keeping Entity sealed from access
public record UserDTO (
        Long id,
        String username,
        double gold,
        double dollars,
        LocalDate createdAt,
        List<RollDTO> rolls
){}
