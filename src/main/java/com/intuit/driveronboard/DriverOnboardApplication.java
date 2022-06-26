package com.intuit.driveronboard;

import com.intuit.driveronboard.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class DriverOnboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriverOnboardApplication.class, args);
	}

}
