package com.highgarden.springboot_board;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class SpringbootBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBoardApplication.class, args);
	}

}
