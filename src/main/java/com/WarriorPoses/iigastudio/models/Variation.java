package com.WarriorPoses.iigastudio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class Variation {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="YogaPose_id", nullable=false)
    private WarriorPose warriorPose;
}
