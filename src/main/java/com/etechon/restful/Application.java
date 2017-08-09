package com.etechon.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author Gagan.tyagi
 * This method is used to initialize the all configuration 
 */

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		System.setProperty("server.port","8090"); // for runing on different tomcat port instead of 8080
		SpringApplication.run(Application.class, args);
	}
}
