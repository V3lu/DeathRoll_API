package com.deathroll.DeathRoll.Repositories;

import com.deathroll.DeathRoll.Models.Roll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RollRepository extends JpaRepository<Roll, Long> {

}
