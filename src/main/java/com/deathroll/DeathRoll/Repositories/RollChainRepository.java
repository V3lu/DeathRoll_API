package com.deathroll.DeathRoll.Repositories;

import com.deathroll.DeathRoll.Models.RollChain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface RollChainRepository extends JpaRepository<RollChain, Long>, JpaSpecificationExecutor<RollChain> {
    Optional<RollChain> findByIsActive(boolean isActive);
}
