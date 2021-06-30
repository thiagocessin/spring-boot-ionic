package com.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	// executa alguma ação quando a aplicação inicia
	// usando MySql executar somente uma vez para carga na tabela
	@Override
	public void run(String... args) throws Exception {
	}

	

}
