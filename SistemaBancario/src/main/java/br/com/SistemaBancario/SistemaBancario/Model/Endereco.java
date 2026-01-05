package br.com.SistemaBancario.SistemaBancario.Model;

import br.com.SistemaBancario.SistemaBancario.Exceptions.CEPException;
import br.com.SistemaBancario.SistemaBancario.Service.ConsumeApi;
import br.com.SistemaBancario.SistemaBancario.Service.Conversor;
import br.com.SistemaBancario.SistemaBancario.Service.Dados_Conta;
import jakarta.persistence.*;

import java.util.Scanner;

@Entity
@Table(name = "enderecos")
public class Endereco {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cidade;
    private String estado;
    private String bairro;
    private String logradouro;
    @OneToOne(mappedBy = "endereco")
    private Dados_Conta dadosConta;

    @Transient
    private final String URL = "https://viacep.com.br/ws/";
    @Transient
    private final String URL_FINAL = "/json/";
    @Transient
    private ConsumeApi consume = new ConsumeApi();
    @Transient
    private Conversor conv = new Conversor();


    @Transient
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
    public Endereco CriarEndereco(){

        while(true) {
            try {
                System.out.println("Vamos adicionar o seu endereco agora : ");
                System.out.println("Digite o seu CEP :");
                var CEP = leitura.nextLine();
                if (CEP.length() < 8 || CEP.length() > 8) {
                    throw new CEPException("Cep invalido, deve conter 8 digitos");
                }
                var json = consume.Consume(URL + CEP + URL_FINAL);
                CepData cepData = conv.ObterDados(json, CepData.class);

                Endereco enderecoCompleto = new Endereco(cepData);

                System.out.println("Endereco criado com sucesso");
                System.out.println(enderecoCompleto);

                return enderecoCompleto;
            }catch (CEPException e){
                System.out.println("Erro : " + e.getMessage());
                System.out.println("Tente novamente !");
            }
        }
    }


}
