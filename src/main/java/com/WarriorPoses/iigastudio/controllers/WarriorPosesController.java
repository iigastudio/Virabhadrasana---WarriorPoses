package com.WarriorPoses.iigastudio.controllers;

import com.WarriorPoses.iigastudio.models.WarriorPose;
import com.WarriorPoses.iigastudio.repositories.WarriorPoseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/warrior-poses")
public class WarriorPosesController {


        @Autowired
        WarriorPoseRepository warriorPoseRepository;

        @GetMapping("/")
        public Iterable<WarriorPose> getAllWarriorPoses() {
            return warriorPoseRepository.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<WarriorPose> getWarriorPoseById(@PathVariable Long id) {
            Optional<WarriorPose> warriorPose = warriorPoseRepository.findById(id);
            if (warriorPose.isPresent()) {
                return ResponseEntity.ok(warriorPose.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping("/")
        public WarriorPose createWarriorPose(@RequestBody WarriorPose warriorPose) {
            return warriorPoseRepository.save(warriorPose);
        }

        @PutMapping("/{id}")
        public ResponseEntity<WarriorPose> updateWarriorPose(@PathVariable Long id, @RequestBody WarriorPose warriorPose) {
            Optional<WarriorPose> optionalWarriorPose = warriorPoseRepository.findById(id);
            if (optionalWarriorPose.isPresent()) {
                WarriorPose dbWarriorPose = optionalWarriorPose.get();
                dbWarriorPose.setName(warriorPose.getName());
                warriorPoseRepository.save(dbWarriorPose);
                return ResponseEntity.ok(dbWarriorPose);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteWarriorPose(@PathVariable Long id) {
            Optional<WarriorPose> optionalWarriorPose = warriorPoseRepository.findById(id);
            if (optionalWarriorPose.isPresent()) {
                warriorPoseRepository.delete(optionalWarriorPose.get());
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }


    }

