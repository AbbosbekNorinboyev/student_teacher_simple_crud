package uz.pdp.simple_crud2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SimpleCrud2Application {
	public static void main(String[] args) {
		SpringApplication.run(SimpleCrud2Application.class, args);
	}
}
