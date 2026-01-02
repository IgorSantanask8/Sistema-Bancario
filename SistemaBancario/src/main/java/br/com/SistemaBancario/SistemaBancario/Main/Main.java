package br.com.SistemaBancario.SistemaBancario.Main;

import br.com.SistemaBancario.SistemaBancario.Model.Dados_Conta;
import br.com.SistemaBancario.SistemaBancario.Model.Endereco;
import br.com.SistemaBancario.SistemaBancario.repository.ContasRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {


    private Dados_Conta dadosConta = new Dados_Conta();
    private Endereco endereco = new Endereco();
    private List<Dados_Conta> contas = new ArrayList<>();
    private List<Endereco> enderecos = new ArrayList<>();
    private Scanner leitura = new Scanner(System.in);
    private ContasRepository repositorio;

    public Main(ContasRepository repositorio) {
        this.repositorio = repositorio;
    }

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
                    Dados_Conta novaConta = new Dados_Conta();
                    novaConta.CriarConta();

                    Endereco novoendereco = new Endereco();
                    Endereco enderecoCompleto = novoendereco.CriarEndereco();

                    novaConta.setEndereco(enderecoCompleto);
                    repositorio.save(novaConta);

                    System.out.println("Conta e endereco criada com sucesso");
                    break;
                case 2:
                    ExibirDados();
                    break;
                case 3:
                    System.out.println("Digite o CPF da conta para realizar um deposito : ");
                    String cpfDeposito = leitura.nextLine();
                    Optional<Dados_Conta> contaOptional = repositorio.findByCpf(cpfDeposito);

                    if(contaOptional.isPresent()){
                        Dados_Conta contaEncontrada = contaOptional.get();
                        contaEncontrada.Depositar();
                        repositorio.save(contaEncontrada);
                    }else{
                        System.out.println("Conta nao encontrada");
                    }
                    break;
                case 4:
                    System.out.println("Digite o CPF da conta para sacar :");
                    String cpfSaque = leitura.nextLine();
                    Optional<Dados_Conta> contaSaqueOpt = repositorio.findByCpf(cpfSaque);

                    if(contaSaqueOpt.isPresent()){
                        Dados_Conta conta = contaSaqueOpt.get();
                        conta.Sacar();
                        repositorio.save(conta);
                    }else{
                        System.out.println("conta nao encontrada");
                    }
                    break;
                case 5:
                    System.out.println("Saindo ...");
                    break;
                default:
                    System.out.println("Entrada invalida");
                    break;
            }
        }



    }


    public void ExibirDados(){
        List<Dados_Conta> listaContas = repositorio.findAll();

        if(listaContas.isEmpty()) {
            System.out.println("Nenhuma conta encontrada");
        }else{
            listaContas.forEach(c -> {
                System.out.println(c);
                if(c.getEndereco() != null){
                    System.out.println("Endereco : " + c.getEndereco());
                }
            });
        }
    }


}
