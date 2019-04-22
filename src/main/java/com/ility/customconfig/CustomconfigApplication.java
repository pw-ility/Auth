package com.ility.customconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CustomconfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomconfigApplication.class, args);
	}

}

