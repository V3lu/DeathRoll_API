package com.deathroll.DeathRoll.DTOs;

import java.util.List;

public record RollChainDTO (
    Long id,
    List<RollDTO> rolls
){};
