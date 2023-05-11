package com.WarriorPoses.iigastudio.controllers;

import com.WarriorPoses.iigastudio.models.Variation;
import com.WarriorPoses.iigastudio.models.WarriorPose;
import com.WarriorPoses.iigastudio.repositories.VariationRepository;
import com.WarriorPoses.iigastudio.repositories.WarriorPoseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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
    private String getImageUrl(HttpServletRequest request, String imagePath) {
        String baseUrl = getBaseUrl(request);
        return baseUrl + "/images/" + getFileName(imagePath);
    }

    private String getBaseUrl(HttpServletRequest request) {
        String baseUrl = request.getRequestURL().toString();
        return baseUrl.replace(request.getServletPath(), "");
    }

    private String getFileName(String imagePath) {
        return imagePath.substring(imagePath.lastIndexOf("/") + 1);
    }
    @PostMapping("/{warriorPoseId}")
    public ResponseEntity<Variation> createVariation(HttpServletRequest request, @PathVariable Long warriorPoseId, @RequestParam("image") MultipartFile imageFile,@RequestParam("name") String variationName) {
        String userRole = request.getHeader("X-User-Role");

        if ("ADMIN".equals(userRole)) {
            try {
                // Save the image to a folder or a cloud storage service
                String imageName = imageFile.getOriginalFilename();
                String projectDir = System.getProperty("user.dir");
                String imagePath = projectDir + "/images/" + imageName;
                Path destination = Path.of(imagePath);
                Files.copy(imageFile.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

                // Create a new Variation object and set the image path
                Variation variation = new Variation();
                variation.setName(variationName);

                variation.setImageUrl(getImageUrl(request, imagePath)); // Set the image URL

                Optional<WarriorPose> optionalWarriorPose = warriorPoseRepository.findById(warriorPoseId);
                if (optionalWarriorPose.isPresent()) {
                    WarriorPose warriorPose = optionalWarriorPose.get();
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
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            // Handle unauthorized access
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Variation> updateVariation(HttpServletRequest request, @PathVariable Long id, @RequestParam("image") MultipartFile imageFile, @RequestParam("name") String variationName ) {
        String userRole = request.getHeader("X-User-Role");


            try {
                // Save the image to a folder or a cloud storage service
                String imageName = imageFile.getOriginalFilename();
                String projectDir = System.getProperty("user.dir");
                String imagePath = projectDir + "/images/" + imageName;
                Path destination = Path.of(imagePath);
                Files.copy(imageFile.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

                // Find the existing Variation object
                Optional<Variation> optionalVariation = variationRepository.findById(id);
                if (optionalVariation.isPresent()) {
                    Variation variation = optionalVariation.get();

                    // Update the Variation details
                    variation.setName(variationName);

                    variation.setImageUrl(getImageUrl(request, imagePath)); // Set the image URL

                    variationRepository.save(variation);
                    return ResponseEntity.ok(variation);
                } else {
                    return ResponseEntity.notFound().build();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVariation(HttpServletRequest request,@PathVariable Long id) {
        String userRole = request.getHeader("X-User-Role");

            Optional<Variation> optionalVariation = variationRepository.findById(id);
            if (optionalVariation.isPresent()) {
                variationRepository.delete(optionalVariation.get());
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }


}
