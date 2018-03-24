package ed.mse.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ed.mse.commons.Logger;

@SpringBootApplication
public class ServiceApplication {

	public static void main(String[] args) {
		Logger.instantiate(Logger.DEBUG);
		SpringApplication.run(ServiceApplication.class, args);
	}
}
