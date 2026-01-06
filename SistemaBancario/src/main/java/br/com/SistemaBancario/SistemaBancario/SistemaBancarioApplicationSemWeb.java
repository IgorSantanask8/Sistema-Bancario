package br.com.SistemaBancario.SistemaBancario;

import br.com.SistemaBancario.SistemaBancario.repository.ContasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaBancarioApplicationSemWeb {

    @Autowired
    private ContasRepository repositorio;

	public static void main(String[] args) {
		SpringApplication.run(SistemaBancarioApplicationSemWeb.class, args);
	}


}
