package com.example.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //AuditingListener 사용하고 싶으면
public class BootJpaBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootJpaBasicApplication.class, args);
	}

}
