package br.com.SistemaBancario.SistemaBancario.Model;

import br.com.SistemaBancario.SistemaBancario.Exceptions.CPFException;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

@Entity
@Table(name = "Contas")
public class Dados_Conta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    @Column(unique = true)
    private String cpf;
    private Double renda;
    private Double saldo = 0.0;
    private String dataDeNascimento;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "endereco_id",referencedColumnName = "id")
    private Endereco endereco;

    public Dados_Conta(){}

    public Dados_Conta(String nome, String CPF, Double renda, String dataDeNascimento,Double saldo) {
        this.nome = nome;
        this.cpf = CPF;
        this.renda = renda;
        this.dataDeNascimento = dataDeNascimento;
        this.saldo = saldo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String CPF) {
        this.cpf = CPF;
    }

    public Double getRenda() {
        return renda;
    }

    public void setRenda(Double renda) {
        this.renda = renda;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void CriarConta() {
        Scanner leitura = new Scanner(System.in);
        System.out.println("Digite seu nome : [Nome e sobrenome(final)]");
        setNome(leitura.nextLine());

        boolean cpfValido = false;
        while(!cpfValido) {
            try {
                System.out.println("Digite seu CPF corretamente :");
                String cpfInformado = leitura.nextLine();
                if (cpfInformado.length() < 11 || cpfInformado.length() > 11) {
                    throw new CPFException("Cpf deve conter 11 digitos !");
                }
                setCPF(cpfInformado);
                cpfValido = true;
            } catch (CPFException e) {
                System.out.println("Erro !" + e.getMessage());
                System.out.println("Tente de novo");
            }
        }
        System.out.println("Digite sua renda atual:");
        setRenda(leitura.nextDouble());
        leitura.nextLine();
        try {
            System.out.println("Digite sua data de nascimento no formato : yyyy/MM/dd");
            DateTimeFormatter formatadorEntrada = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            DateTimeFormatter formatadorSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            setDataDeNascimento(leitura.nextLine());
            LocalDate date = LocalDate.parse(getDataDeNascimento(), formatadorEntrada);
            String dataFormatada = date.format(formatadorSaida);
            setDataDeNascimento(dataFormatada);
        }catch(DateTimeParseException e){
            System.out.println("Erro, data invalida");
        }
        System.out.println("Conta criada com sucesso");
    }

    public void Depositar(){
        Scanner leitura = new Scanner(System.in);
        System.out.println("\nQuanto dinheiro deseja depositar em sua conta : ");
        double valor = leitura.nextDouble();
        leitura.nextLine();

        if(valor <= 0){
            System.out.println("Nao e possivel depositar um valor menor ou igual a zero!");
        }else {
            saldo += valor;
            System.out.println("Deposito realizado com sucesso!");
            System.out.println("Saldo atualizado $: " + getSaldo());
        }
    }

    public void Sacar(){
        Scanner leitura = new Scanner(System.in);
        System.out.println("\nDigite o valor que deseja sacar:");
        double valor = leitura.nextDouble();
        leitura.nextLine();

        if(valor > saldo){
            System.out.println("Nao e possivel sacar um valor maior que seu saldo !");
        }else if(valor <= 0){
            System.out.println("Nao e possivel sacar um valor menor ou igual a zero");
        }else{
            setSaldo(saldo -= valor);
            System.out.println("Saldo atualizado $ : " + getSaldo());
        }
    }

    @Override
    public String toString() {
        return "Nome do titular : " + nome + "|Cpf Cadastrado : " + cpf + "|Saldo atual : " + saldo + "|Renda :" + renda +
                "|Data de nascimento : " + dataDeNascimento ;
    }
}
