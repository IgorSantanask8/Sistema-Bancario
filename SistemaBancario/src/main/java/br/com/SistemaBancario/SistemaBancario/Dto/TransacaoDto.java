package br.com.SistemaBancario.SistemaBancario.Dto;

import java.time.LocalDateTime;

public record TransacaoDto(String tipo,
                           double valor,
                           LocalDateTime dataTransacao) {
}
