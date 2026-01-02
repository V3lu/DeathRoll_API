package com.deathroll.DeathRoll.Repositories;

import com.deathroll.DeathRoll.Models.Roll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RollRepository extends JpaRepository<Roll, Long>, JpaSpecificationExecutor<Roll> {
    // SELECT user_id FROM roll WHERE rollBase < prevRoll.rollBase + 500 AND prevRoll.rollBase > param - 500

}
