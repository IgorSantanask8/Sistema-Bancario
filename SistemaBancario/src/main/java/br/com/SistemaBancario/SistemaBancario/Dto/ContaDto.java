package br.com.SistemaBancario.SistemaBancario.Dto;

import br.com.SistemaBancario.SistemaBancario.Model.Transacao;
import jakarta.persistence.Column;

import java.util.List;

public record ContaDto(String nome,
                       String cpf, Double renda,
                       Double saldo,
                       String dataDeNascimento) {
}
