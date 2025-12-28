package com.deathroll.DeathRoll.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
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
    @JsonIgnore
    private List<Roll> rolls = new ArrayList<>();

}
