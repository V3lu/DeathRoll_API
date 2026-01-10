package com.deathroll.DeathRoll.Repositories.Specifications;

import com.deathroll.DeathRoll.Models.RollChain;
import org.springframework.data.jpa.domain.Specification;

public class RollChainSpecification {

    public static Specification<RollChain> hasUserIdEqualTo(Long userId){
        return (root, query, cb) -> {
            if (userId == null) return null;

            return cb.equal(root.get("user").get("id"), userId);
        };
    }

    public static Specification<RollChain> hasIsActiveStatus(boolean isActive){
        return ((root, query, cb) -> cb.equal(root.get("isActive"), isActive));
    }
}
