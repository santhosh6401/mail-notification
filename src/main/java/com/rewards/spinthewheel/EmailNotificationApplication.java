package com.rewards.spinthewheel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.rewards.spinthewheel"})
@EnableMongoRepositories("com.rewards.spinthewheel.repository")
@EnableJpaRepositories("com.rewards.spinthewheel.repository")
@EnableScheduling
public class EmailNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailNotificationApplication.class, args);
	}

}
