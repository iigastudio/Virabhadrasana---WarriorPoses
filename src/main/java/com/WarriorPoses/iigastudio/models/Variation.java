package com.WarriorPoses.iigastudio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC) // Update the access level of the constructor
@AllArgsConstructor
public class Variation {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "WarriorPose_id", nullable = false)
    private WarriorPose warriorPose;


    private String imageUrl;
 }