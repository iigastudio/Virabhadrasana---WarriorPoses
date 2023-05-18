package com.WarriorPoses.iigastudio;

import com.WarriorPoses.iigastudio.models.Variation;
import com.WarriorPoses.iigastudio.models.WarriorPose;
import com.WarriorPoses.iigastudio.repositories.VariationRepository;
import com.WarriorPoses.iigastudio.repositories.WarriorPoseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class WarriorPosesApplication implements CommandLineRunner {
    @Autowired
    private WarriorPoseRepository warriorPoseRepository;
    @Autowired
    private VariationRepository variationRepository;

    public static void main(String[] args) {
        SpringApplication.run(WarriorPosesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        WarriorPose warrior1 = new WarriorPose();
        warrior1.setName("Virabhadrasana I");

        warrior1.setImageUrl("http://localhost:8090/images/Virabhadrasana1.png");

        Variation var1war1 = new Variation();
        var1war1.setName("Variation 1");

        var1war1.setImageUrl("http://localhost:8090/images/Virabhadrasana1Variation1.png");
        var1war1.setWarriorPose(warrior1);

        Variation var2war1 = new Variation();
        var2war1.setName("Variation 2");

        var2war1.setImageUrl("http://localhost:8090/images/Virabhadrasana1Variation2.png");
        var2war1.setWarriorPose(warrior1);


        // Save the WarriorPose before saving the Variations
        warriorPoseRepository.save(warrior1);

        // Set the WarriorPose for the Variations
        var1war1.setWarriorPose(warrior1);
        var2war1.setWarriorPose(warrior1);

        // Save the Variations
        variationRepository.save(var1war1);
        variationRepository.save(var2war1);


        //WarriorPose2
        WarriorPose warrior2 = new WarriorPose();
        warrior2.setName("Virabhadrasana II");

        warrior2.setImageUrl("http://localhost:8090/images/Virabhadrasana2.png");


        Variation var1war2 = new Variation();
        var1war2.setName("Variation 1 ");
        var1war2.setImageUrl("http://localhost:8090/images/Virabhadrasana2Variation1.png");
        var1war2.setWarriorPose(warrior2);


        Variation var2war2 = new Variation();
        var2war2.setName("Variation 2");
        var2war2.setImageUrl("http://localhost:8090/images/Virabhadrasana2Variation2.png");
        var2war2.setWarriorPose(warrior2);


        // Save the WarriorPose before saving the Variations
        warriorPoseRepository.save(warrior2);

        // Set the WarriorPose for the Variations
        var1war2.setWarriorPose(warrior2);
        var2war2.setWarriorPose(warrior2);
        // Save the Variations
        variationRepository.save(var1war2);
        variationRepository.save(var2war2);

        //WarriorPose3
        WarriorPose warrior3 = new WarriorPose();
        warrior3.setName("Virabhadrasana III");

        warrior3.setImageUrl("http://localhost:8090/images/Virabhadrasana3.png");


        Variation var1war3 = new Variation();
        var1war3.setName("Variation 1");
        var1war3.setImageUrl("http://localhost:8090/images/Virabhadrasana3Variation1.png");
        var1war3.setWarriorPose(warrior3);

        Variation var2war3 = new Variation();
        var2war3.setName("Variation 2");
        var2war3.setImageUrl("http://localhost:8090/images/Virabhadrasana3Variation2.png");
        var2war3.setWarriorPose(warrior3);


        // Save the WarriorPose before saving the Variations
        warriorPoseRepository.save(warrior3);

        // Set the WarriorPose for the Variations
        var1war3.setWarriorPose(warrior3);
        var2war3.setWarriorPose(warrior3);


        // Save the Variations
        variationRepository.save(var1war3);
        variationRepository.save(var2war3);
    }
}