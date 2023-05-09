package com.WarriorPoses.iigastudio;

import com.WarriorPoses.iigastudio.models.Variation;
import com.WarriorPoses.iigastudio.models.WarriorPose;
import com.WarriorPoses.iigastudio.repositories.VariationRepository;
import com.WarriorPoses.iigastudio.repositories.WarriorPoseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


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
		warrior1.setName("Virabhadrasana1");
		warrior1.setDescription("Virabhadrasana1 Description");
		warrior1.setImageUrl("http://localhost:8090/images/Virabhadrasana1.jpg");

		Variation var1 = new Variation();
		var1.setName("Variation1 for Virabhadrasana1");
		var1.setDescription("Variation1 Description");
		var1.setImageUrl("http://localhost:8090/images/var1warrior1.jpg");
		var1.setWarriorPose(warrior1);



		// Save the WarriorPose before saving the Variations
		warriorPoseRepository.save(warrior1);

		// Set the WarriorPose for the Variations
		var1.setWarriorPose(warrior1);

		// Save the Variations
		variationRepository.save(var1);


		//WarriorPose2
		WarriorPose warrior2 = new WarriorPose();
		warrior2.setName("Virabhadrasana2");
		warrior2.setDescription("Virabhadrasana2 Description");
		warrior2.setImageUrl("http://localhost:8090/images/Virabhadrasana2.jpg");


		Variation var2 = new Variation();
		var2.setName("Variation2 for Virabhadrasana2");
		var2.setDescription("Variation2 Description");
		var2.setImageUrl("http://localhost:8090/images/var2warrior2.jpg");
		var2.setWarriorPose(warrior2);


		// Save the WarriorPose before saving the Variations
		warriorPoseRepository.save(warrior2);

		// Set the WarriorPose for the Variations
		var2.setWarriorPose(warrior2);

		// Save the Variations
		variationRepository.save(var2);

		//WarriorPose3
		WarriorPose warrior3 = new WarriorPose();
		warrior3.setName("Virabhadrasana3");
		warrior3.setDescription("Virabhadrasana3 Description");
		warrior3.setImageUrl("http://localhost:8090/images/Virabhadrasana3.jpg");


		Variation var3 = new Variation();
		var3.setName("Variation3 for Virabhadrasana3");
		var3.setDescription("Variation3 Description");
		var3.setImageUrl("http://localhost:8090/images/var3warrior3.jpg");
		var3.setWarriorPose(warrior3);


		// Save the WarriorPose before saving the Variations
		warriorPoseRepository.save(warrior3);

		// Set the WarriorPose for the Variations
		var3.setWarriorPose(warrior3);

		// Save the Variations
		variationRepository.save(var3);
	}
}