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
        public Iterable<WarriorPose> getAllYogaPoses() {
            return warriorPoseRepository.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<WarriorPose> getYogaPoseById(@PathVariable Long id) {
            Optional<WarriorPose> yogaPose = warriorPoseRepository.findById(id);
            if (yogaPose.isPresent()) {
                return ResponseEntity.ok(yogaPose.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping("/")
        public WarriorPose createYogaPose(@RequestBody WarriorPose warriorPose) {
            return warriorPoseRepository.save(warriorPose);
        }

        @PutMapping("/{id}")
        public ResponseEntity<WarriorPose> updateYogaPose(@PathVariable Long id, @RequestBody WarriorPose warriorPose) {
            Optional<WarriorPose> optionalYogaPose = warriorPoseRepository.findById(id);
            if (optionalYogaPose.isPresent()) {
                WarriorPose dbWarriorPose = optionalYogaPose.get();
                dbWarriorPose.setName(warriorPose.getName());
                warriorPoseRepository.save(dbWarriorPose);
                return ResponseEntity.ok(dbWarriorPose);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteYogaPose(@PathVariable Long id) {
            Optional<WarriorPose> optionalYogaPose = warriorPoseRepository.findById(id);
            if (optionalYogaPose.isPresent()) {
                warriorPoseRepository.delete(optionalYogaPose.get());
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }


    }

