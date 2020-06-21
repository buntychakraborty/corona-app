package io.corona.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CoronaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronaApiApplication.class, args);
	}

}
