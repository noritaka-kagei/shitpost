package com.noritakakagei.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot Launcher class
 */
@SpringBootApplication
public class ShitpostApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShitpostApplication.class, args)
			.getBean(ShitpostApplication.class)
			.execute();
	}

	/**
	 * Dependency Injection
	 */
	@Autowired
	Greet greet;

	public void execute() {
		greet.greeting();
	}

}
