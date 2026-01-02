package br.com.SistemaBancario.SistemaBancario;

import br.com.SistemaBancario.SistemaBancario.Main.Main;
import br.com.SistemaBancario.SistemaBancario.repository.ContasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaBancarioApplication implements CommandLineRunner {

    @Autowired
    private ContasRepository repositorio;

	public static void main(String[] args) {
		SpringApplication.run(SistemaBancarioApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Main princ = new Main(repositorio);
        princ.Show_Menu();
    }
}
