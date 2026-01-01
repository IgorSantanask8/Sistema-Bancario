package br.com.SistemaBancario.SistemaBancario.Main;

import br.com.SistemaBancario.SistemaBancario.Exceptions.CEPException;
import br.com.SistemaBancario.SistemaBancario.Model.CepData;
import br.com.SistemaBancario.SistemaBancario.Model.Dados_Conta;
import br.com.SistemaBancario.SistemaBancario.Model.Endereco;
import br.com.SistemaBancario.SistemaBancario.Service.ConsumeApi;
import br.com.SistemaBancario.SistemaBancario.Service.Conversor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final String URL = "https://viacep.com.br/ws/";
    private final String URL_FINAL = "/json/";
    private ConsumeApi consume = new ConsumeApi();
    private Conversor conv = new Conversor();
    private Dados_Conta dadosConta = new Dados_Conta();
    private List<Dados_Conta> contas = new ArrayList<>();
    private List<Endereco> enderecos = new ArrayList<>();
    private Scanner leitura = new Scanner(System.in);

    public void Show_Menu(){
        var menu = """
                ==================MENU================== 
                1 - Criar conta     |2 - Consultar dados
                3 - Depositar valor     |4 - Sacar valor
                5 - Sair
                """;

        Integer option_menu= -1;
        while(option_menu!=5){
            System.out.println(menu);
            option_menu = leitura.nextInt();
            leitura.nextLine();

            switch (option_menu){
                case 1:
                    dadosConta.CriarConta();
                    contas.add(dadosConta);
                    CriarEndereco();
                    break;
                case 2:
                    ExibirDados();
                    break;
                case 3:
                    dadosConta.Depositar();
                    break;
                case 4:
                    dadosConta.Sacar();
                    break;
                case 5:
                    System.out.println("Saindo ...");
                default:
                    System.out.println("Entrada invalida");
                    break;
            }
        }



    }

    public void CriarEndereco(){
        boolean cepValido = false;

        while(!cepValido) {
            try {
                System.out.println("Vamos adicionar o seu endereco agora : ");
                System.out.println("Digite o seu CEP :");
                var CEP = leitura.nextLine();
                if (CEP.length() < 8 || CEP.length() > 8) {
                    throw new CEPException("Cep invalido, deve conter 8 digitos");
                }
            var json = consume.Consume(URL + CEP + URL_FINAL);
            CepData cepData = conv.ObterDados(json, CepData.class);
            Endereco adress = new Endereco(cepData);
            System.out.println("Endereco criado com sucesso");
            System.out.println(adress);
            enderecos.add(adress);
            cepValido = true;
            }catch (CEPException e){
                System.out.println("Erro : " + e.getMessage());
                System.out.println("Tente novamente !");
            }
        }
    }

    public void ExibirDados(){
        contas.stream()
                .forEach(c -> {
                    if(c.getSaldo() == null){
                        c.setSaldo(0.0);
                    }
                    System.out.println("Nome do titular :" + c.getNome() + "|Cpf cadstrado : " + c.getCPF() + "|Saldo : " + c.getSaldo() +
                            "|Renda atual : " + c.getRenda() + "|Data de nascimento : " + c.getDataDeNascimento());
                })
        ;
        enderecos.stream()
                .forEach(e -> System.out.println(e));
    }

}
