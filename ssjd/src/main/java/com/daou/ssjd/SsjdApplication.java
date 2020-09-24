package com.daou.ssjd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SsjdApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsjdApplication.class, args);
	}

}
