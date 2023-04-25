package com.WarriorPoses.iigastudio.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)

public class WarriorPose {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @OneToMany(mappedBy="warriorPose")
    private Set<Variation> variations;
}
