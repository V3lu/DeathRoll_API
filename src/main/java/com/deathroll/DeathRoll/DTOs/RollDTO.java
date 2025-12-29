package com.deathroll.DeathRoll.DTOs;

public record RollDTO (
        Long id,
        String username,
        int rollBase,
        int rollNumber
){}
