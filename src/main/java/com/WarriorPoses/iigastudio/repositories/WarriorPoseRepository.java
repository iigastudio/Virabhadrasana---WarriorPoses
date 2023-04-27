package com.WarriorPoses.iigastudio.repositories;

import com.WarriorPoses.iigastudio.models.WarriorPose;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WarriorPoseRepository extends CrudRepository<WarriorPose, Long> {
    Optional<WarriorPose> findByName(String name);
    @Query("SELECT wp FROM WarriorPose wp JOIN wp.variations v WHERE LOWER(v.name) LIKE LOWER(CONCAT('%', :variationName, '%'))")
    Optional<WarriorPose> findByVariationNameIgnoreCaseContaining(@Param("variationName") String variationName);
    Optional<WarriorPose> findByNameIgnoreCaseContaining(String name);
}
