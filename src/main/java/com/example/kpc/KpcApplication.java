package com.example.kpc;

import com.example.kpc.configurations.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class KpcApplication {

	public static void main(String[] args) {
		SpringApplication.run(KpcApplication.class, args);
	}

}
