package com.deathroll.DeathRoll.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String email;

    private String hashedPassword;

    private double gold;

    private double dollars;

    private LocalDate createdAt;

    @OneToMany(mappedBy = "user")
    private List<Roll> rolls;

}
