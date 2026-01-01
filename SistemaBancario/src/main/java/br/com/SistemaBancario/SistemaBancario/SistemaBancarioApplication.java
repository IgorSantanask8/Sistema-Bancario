package br.com.SistemaBancario.SistemaBancario;

import br.com.SistemaBancario.SistemaBancario.Main.Main;
import br.com.SistemaBancario.SistemaBancario.Service.ConsumeApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaBancarioApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SistemaBancarioApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Main princ = new Main();
        princ.Show_Menu();
    }
}
