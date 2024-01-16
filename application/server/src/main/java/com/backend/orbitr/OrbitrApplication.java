package com.backend.orbitr;

import com.backend.orbitr.bll.config.RsaKeyProperties;
import com.backend.orbitr.bll.config.SystemLoggerThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class OrbitrApplication {

	public static void main(String[] args) {
		SystemLoggerThread logger = new SystemLoggerThread();
		SpringApplication.run(OrbitrApplication.class, args);
		logger.start();
	}
}
