package com.deathroll.DeathRoll.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "rollchains")
@NoArgsConstructor
public class RollChain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Excluded to avoid infinite JSON loop
    @ManyToOne
    @JoinColumn(name="user_id")
    @ToString.Exclude
    private User user;

    @Column(columnDefinition="BOOLEAN DEFAULT false")
    private boolean isActive;

    @OneToMany(mappedBy = "roll")
    @ToString.Exclude
    @JsonIgnore
    private List<Roll> rolls = new ArrayList<>();
}
