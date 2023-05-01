package com.WarriorPoses.iigastudio.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC) // Update the access level of the constructor
public class WarriorPose {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "warriorPose")
    private Set<Variation> variations;

    private String imageUrl; // New transient field for image URL
 }