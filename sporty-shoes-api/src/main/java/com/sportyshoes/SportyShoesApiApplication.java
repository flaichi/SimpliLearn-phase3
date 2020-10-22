package com.sportyshoes;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sportyshoes.entities.Customer;
import com.sportyshoes.entities.Shoe;
import com.sportyshoes.entities.enumerations.BrandEnum;
import com.sportyshoes.entities.enumerations.ColorEnum;
import com.sportyshoes.entities.enumerations.GenderEnum;
import com.sportyshoes.entities.enumerations.SizeEnum;
import com.sportyshoes.entities.enumerations.SportEnum;
import com.sportyshoes.entities.enumerations.TerrainEnum;
import com.sportyshoes.entities.enumerations.TypeEnum;
import com.sportyshoes.repositories.CustomerRepository;
import com.sportyshoes.repositories.ShoesRepository;
import com.sportyshoes.services.AccountService;

@SpringBootApplication
public class SportyShoesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportyShoesApiApplication.class, args);
	}

	// uncomment this to put a shoe in the database
	
	  @Bean CommandLineRunner start(ShoesRepository repo) { return args ->{ Shoe
	  shoe = new Shoe(); shoe.setBrand(BrandEnum.NIKE);
	  shoe.setColor(ColorEnum.RED);
	  shoe.setDescription("great running shoes for girls");
	  shoe.setGender(GenderEnum.GIRLS); shoe.setName("Nike gtr2");
	  shoe.setSize(SizeEnum.Three_five); shoe.setSport(SportEnum.RUNNING);
	  shoe.setTerrain(TerrainEnum.ROAD); shoe.setType(TypeEnum.RACING_SHOES);
	  
	  System.out.println(repo.save(shoe).getBrand()); }; }
	  
	  // uncomment this to put a customer in the database
	  



/*
 * @Bean CommandLineRunner startCustomer(CustomerRepository repo1) { return arg
 * ->{ Customer customer = new Customer(); customer.setName("a given customer");
 * customer.setRegisterDate(LocalDateTime.now()); repo1.save(customer);
 * 
 * System.out.println(customer.getName()); }; }
 * 
 * 
 * // uncomment this to put users and and admin in the database
 * 
 * 
 * 
 * 
 * @Bean CommandLineRunner startService(AccountService service) { return arg ->{
 * service.saveRole("USER_READ"); service.saveRole("USER_WRITE");
 * service.saveRole("ADMIN_READ"); service.saveRole("ADMIN_WRITE");
 * service.saveUser("user1", "Upass2", "Upass2"); service.saveAdmin("admin1",
 * "Apass1", "Apass1");
 * 
 * }; }
 */
 
 
	 
	
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
}
