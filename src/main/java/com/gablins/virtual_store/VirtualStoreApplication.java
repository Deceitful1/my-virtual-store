package com.gablins.virtual_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication(scanBasePackages ="com.gablins")
@Profile("dev")

public class VirtualStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualStoreApplication.class, args);
	}

}
