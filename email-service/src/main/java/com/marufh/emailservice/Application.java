package com.marufh.emailservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
@ComponentScan({"com.marufh.emailcore.config", "com.marufh.emailservice"})
public class Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		Environment env = app.run(args).getEnvironment();
		String protocol = "http";
		log.info("\n----------------------------------------------------------\n\t" +
						"Application '{}' is running :) \n\t" +
						"Access URL: \t{}://localhost:{}\n\t" +
						"Version: \t{} \n\t" +
						"Profile(s): \t{}\n----------------------------------------------------------",
				env.getProperty("app.name"),
				protocol,
				env.getProperty("server.port"),
				env.getProperty("app.version"),
				env.getActiveProfiles());

	}

}
