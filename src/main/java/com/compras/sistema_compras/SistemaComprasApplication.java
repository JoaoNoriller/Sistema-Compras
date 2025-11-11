package com.compras.sistema_compras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaComprasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaComprasApplication.class, args);
		System.out.println("Sistema Iniciado!!");
		System.out.println("Acesse: http://localhost:8080");
	}

}
