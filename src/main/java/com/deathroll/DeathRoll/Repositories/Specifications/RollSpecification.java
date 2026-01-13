package com.deathroll.DeathRoll.Repositories.Specifications;

import com.deathroll.DeathRoll.Models.Roll;
import org.springframework.data.jpa.domain.Specification;

public class RollSpecification {

    public static Specification<Roll> hasRollBaseBetweenBorder500(Integer rollBase){
        return (root, query, cb) -> {
            if (rollBase == null) return null;

            int min = rollBase - 500;
            int max = rollBase + 500;

            return cb.between(root.get("rollBase"), min, max);
        };
    }

    public static Specification<Roll> hasUserIdNotItsOwn(Long loggedUserId){
        return ((root, query, cb) -> {
            if (loggedUserId == null) return null;

            return cb.notEqual(root.get("user").get("id"), loggedUserId);
        });
    }
}
