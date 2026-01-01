package br.com.SistemaBancario.SistemaBancario.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CepData(@JsonProperty("localidade") String cidade,
                      @JsonProperty("uf")String estado,
                      @JsonProperty("bairro")String bairro,
                      @JsonProperty("logradouro")String logradouro) {
}
