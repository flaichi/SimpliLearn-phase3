package com.sportyshoes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sportyshoes.entities.Shoe;
import com.sportyshoes.entities.enumerations.BrandEnum;
import com.sportyshoes.entities.enumerations.ColorEnum;
import com.sportyshoes.entities.enumerations.GenderEnum;
import com.sportyshoes.entities.enumerations.SizeEnum;
import com.sportyshoes.entities.enumerations.SportEnum;
import com.sportyshoes.entities.enumerations.TerrainEnum;
import com.sportyshoes.entities.enumerations.TypeEnum;
import com.sportyshoes.repositories.ShoesRepository;

@SpringBootApplication
public class SportyShoesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportyShoesApiApplication.class, args);
	}

	// uncomment this to put a shoe in the database
	@Bean
	CommandLineRunner start(ShoesRepository repo) {
		return arg ->{
			Shoe shoe = new Shoe();
			shoe.
			shoe.setBrand(BrandEnum.ADIDAS);
			shoe.setColor(ColorEnum.BLACK);
			shoe.setDescription("great running shoes for boys");
			shoe.setGender(GenderEnum.BOYS);
			shoe.setName("Addidas Rx11");
			shoe.setSize(SizeEnum.Five_five);
			shoe.setSport(SportEnum.RUNNING);
			shoe.setTerrain(TerrainEnum.ROAD);
			shoe.setType(TypeEnum.RACING_SHOES);
			
			System.out.println(repo.save(shoe).getBrand());
		};
	}
}
