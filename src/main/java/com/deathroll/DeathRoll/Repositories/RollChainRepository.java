package com.deathroll.DeathRoll.Repositories;

import com.deathroll.DeathRoll.Models.RollChain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RollChainRepository extends JpaRepository<RollChain, Long> {
    Optional<RollChain> findByIsActive(boolean isActive);
}
