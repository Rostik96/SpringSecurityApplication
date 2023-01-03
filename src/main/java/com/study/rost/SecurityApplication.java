package com.study.rost;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.study.rost.services.PeopleService;
import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class SecurityApplication implements CommandLineRunner {
	private final PeopleService peopleService;
	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Override
	public void run(String... args) {
		peopleService.index().forEach(System.out::println);
	}
}
