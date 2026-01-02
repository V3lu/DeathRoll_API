package com.deathroll.DeathRoll.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Random;

@Data
@Entity
@Table(name = "rolls")
@NoArgsConstructor
public class Roll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Excluded to avoid infinite JSON loop
    @ManyToOne
    @JoinColumn(name="user_id")
    @ToString.Exclude
    private User user;

    private int rollBase;

    private int rolledNumber;
    // SELECT user_id FROM roll WHERE rollBase < param + 500 AND rollbase > param - 500
    private boolean isActive;

    public Roll(int base){
        this.rollBase = base;
        Random random = new Random();
        this.rolledNumber = random.nextInt(this.rollBase) + 1;
    }
}


