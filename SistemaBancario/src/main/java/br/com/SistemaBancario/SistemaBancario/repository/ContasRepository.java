package br.com.SistemaBancario.SistemaBancario.repository;

import br.com.SistemaBancario.SistemaBancario.Service.Dados_Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContasRepository extends JpaRepository<Dados_Conta, Long> {
    Optional<Dados_Conta> findByCpf(String cpf);
}
