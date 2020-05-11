package com.bwzb.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class BwzbSmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BwzbSmsApplication.class, args);
	}

}
