package com.WarriorPoses.iigastudio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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




    @OneToMany(mappedBy = "warriorPose", cascade = CascadeType.ALL) // Add cascade type
    @JsonIgnore
    private List<Variation> variations;

    private String imageUrl;
 }