package br.com.SistemaBancario.SistemaBancario.Dto;

public record EnderecoDto(String cidade,
                          String estado,
                          String bairro,
                          String logradouro,
                          Long id) {
}
