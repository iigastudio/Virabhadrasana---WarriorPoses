package com.WarriorPoses.iigastudio.controllers;

import com.WarriorPoses.iigastudio.models.Variation;
import com.WarriorPoses.iigastudio.models.WarriorPose;
import com.WarriorPoses.iigastudio.repositories.VariationRepository;
import com.WarriorPoses.iigastudio.repositories.WarriorPoseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/variations")
public class VariationsController {


    @Autowired
    VariationRepository variationRepository;
    @Autowired
    WarriorPoseRepository warriorPoseRepository;

    @GetMapping("/")
    public Iterable<Variation> getAllVariations() {
        return variationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Variation> getVariationById(@PathVariable Long id) {
        Optional<Variation> variation = variationRepository.findById(id);
        if (variation.isPresent()) {
            return ResponseEntity.ok(variation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{yogaPoseId}")
    public ResponseEntity<Variation> createVariation(@PathVariable Long yogaPoseId, @RequestBody Variation variation) {
        Optional<WarriorPose> optionalYogaPose = warriorPoseRepository.findById(yogaPoseId);
        if (optionalYogaPose.isPresent()) {
            WarriorPose warriorPose = optionalYogaPose.get();
            variation.setWarriorPose(warriorPose);
            Set<Variation> variations = warriorPose.getVariations();
            variations.add(variation);
            warriorPose.setVariations(variations);
            variationRepository.save(variation);
            warriorPoseRepository.save(warriorPose);
            return ResponseEntity.ok(variation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Variation> updateVariation(@PathVariable Long id, @RequestBody Variation variation) {
        Optional<Variation> optionalVariation = variationRepository.findById(id);
        if (optionalVariation.isPresent()) {
            Variation dbVariation = optionalVariation.get();
            dbVariation.setName(variation.getName());
            variationRepository.save(dbVariation);
            return ResponseEntity.ok(dbVariation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVariation(@PathVariable Long id) {
        Optional<Variation> optionalVariation = variationRepository.findById(id);
        if (optionalVariation.isPresent()) {
            variationRepository.delete(optionalVariation.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
