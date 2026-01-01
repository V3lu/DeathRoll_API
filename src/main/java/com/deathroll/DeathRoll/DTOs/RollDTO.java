package com.deathroll.DeathRoll.DTOs;

// Record exposes only necessary properties while keeping Entity sealed from access
public record RollDTO (
        Long id,
        String username,
        int rollBase,
        int rollNumber
){}
