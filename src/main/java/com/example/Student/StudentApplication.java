package com.example.Student;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}
	
//	@EventListener(ApplicationReadyEvent.class)
//	public void doSomethingOnStartup() {
//		System.out.println("------------------------");
//		System.out.println(LocalDateTime.now());
//		System.out.println("Aplikacija je podignuta.");
//	}
	

}
