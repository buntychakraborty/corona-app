package io.corona.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableScheduling
@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
public class CoronaApiApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(CoronaApiApplication.class, args);
	}
}
