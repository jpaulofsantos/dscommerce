package com.jp.dscommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DscommerceApplication {
	//steps
	//1-começar com a criação de classes com entidades independentes (nas pontas quem falam com uma entidade que tenha muitos *) -> ex: User
	//2-criar classes muitos para 1

	public static void main(String[] args) {

		SpringApplication.run(DscommerceApplication.class, args);
	}

}
