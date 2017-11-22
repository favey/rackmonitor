package com.greywanchuang.rackmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.greywanchuang.rackmonitor.entity")
@EnableJpaRepositories(basePackages = "com.greywanchuang.rackmonitor.repository")
public class RackmonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RackmonitorApplication.class, args);
	}
}
