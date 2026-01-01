package br.com.SistemaBancario.SistemaBancario.Model;

import br.com.SistemaBancario.SistemaBancario.Service.ConsumeApi;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Scanner;

public class Endereco {

    private String cidade;
    private String estado;
    private String bairro;
    private String logradouro;

    private Scanner leitura = new Scanner(System.in);

    public Endereco(){}

    public Endereco(CepData cepData){
        this.cidade = cepData.cidade();
        this.estado = cepData.estado();
        this.bairro = cepData.bairro();
        this.logradouro = cepData.logradouro();
    }

    @Override
    public String toString() {
        return "Endereco : Cidade : " + cidade + "|Estado : " + estado + "|Bairro : " + bairro + "|Logradouro : " + logradouro;
    }


}
