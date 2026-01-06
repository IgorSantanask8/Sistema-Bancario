package br.com.SistemaBancario.SistemaBancario.repository;

import br.com.SistemaBancario.SistemaBancario.Model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
