package br.com.SistemaBancario.SistemaBancario.repository;

import br.com.SistemaBancario.SistemaBancario.Model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByContaId(Long contaId);
}
