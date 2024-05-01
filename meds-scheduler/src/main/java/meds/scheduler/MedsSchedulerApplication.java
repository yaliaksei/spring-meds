package meds.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MedsSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedsSchedulerApplication.class, args);
	}

}
