package com.deathroll.DeathRoll.Models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Random;

@Data
@Entity
@Table(name = "rolls")
public class Roll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private int rollBase;

    private int rolledNumber;

    public Roll(int base){
        this.rollBase = base;
        Random random = new Random();
        this.rolledNumber = random.nextInt(this.rollBase) + 1;
    }
}


