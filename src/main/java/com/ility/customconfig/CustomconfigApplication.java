package com.ility.customconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigServer
@ComponentScan(basePackages = {"com.ility"})
public class CustomconfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomconfigApplication.class, args);
	}

}

