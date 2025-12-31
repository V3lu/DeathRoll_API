package com.deathroll.DeathRoll.DTOs;

import com.deathroll.DeathRoll.Models.User;
import java.util.Optional;

public record TokenUserResponse(String token, UserDTO userDTO){}
