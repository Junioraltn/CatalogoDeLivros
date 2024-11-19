package com.challenge.chellenge;

import com.challenge.chellenge.principal.Principal;
import com.challenge.chellenge.repository.LivroRepository;
import com.challenge.chellenge.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChellengeApplication implements CommandLineRunner {
	@Autowired
	private LivroRepository repositorio;
	@Autowired
	private PessoaRepository pessoaRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(ChellengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio,pessoaRepositorio);
		principal.interagir();
	}
}
