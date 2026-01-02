package com.deathroll.DeathRoll.Repositories.Specifications;

import com.deathroll.DeathRoll.Models.Roll;
import org.springframework.data.jpa.domain.Specification;

public class RollSpecification {

    public static Specification<Roll> hasRollBaseBetweenBorder500(Integer rollBase){

        return (root, query, cb) -> rollBase == null ? null : cb.between(root.get("rollBase"), rollBase - 500, rollBase + 500);
    }
}
