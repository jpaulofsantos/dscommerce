package com.jp.dscommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DscommerceApplication {
	//steps
	//1-começar com a criação de classes com entidades independentes (nas pontas quem falam com uma entidade que tenha muitos *) -> ex: User
	//2-criar classes muitos para 1
	//3-Criar classes 1 para 1
	//4-Criar classes muitos para muitos (um produto pode ter muitas categ e uma categ pode ter varios produtos)
	//5-Criar classes muitos para muitos com classe de associação (presença de chave estrangeira para Order e outra para Produto). Chave primária composta por mais de um campo


	public static void main(String[] args) {

		SpringApplication.run(DscommerceApplication.class, args);
	}

}
