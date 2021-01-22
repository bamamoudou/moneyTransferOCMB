package com.paymybuddy.moneytranfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.paymybuddy.moneytranfer.repositories.UserRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@EnableSwagger2
public class MoneytranferApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneytranferApplication.class, args);
	}

}
