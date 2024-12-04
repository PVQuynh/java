package com.example.java;

import com.example.java.spring_core.aop.ServiceAspect;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class JavaApplication implements CommandLineRunner {
	private final ServiceAspect serviceAspect;

	public static void main(String[] args) {
		SpringApplication.run(JavaApplication.class, args);
	}

	@Override
	public void run(String... args){
		log.info("Truoc khi goi methodAspect");
		try {
			serviceAspect.methodAspect(1);
		} catch (Exception e) {
		}
		log.info("Sau khi goi methodAspect");
	}
}
