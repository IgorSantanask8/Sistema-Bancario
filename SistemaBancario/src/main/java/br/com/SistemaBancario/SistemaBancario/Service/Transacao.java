package br.com.SistemaBancario.SistemaBancario.Service;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String tipo; //Deposito ou Saque
    private double valor;
    private LocalDateTime dataTransacao;

    @ManyToOne
    @JoinColumn(name = "contas_id")
    private Dados_Conta conta;

    public Transacao(){}

    public Transacao(String tipo, double valor, Dados_Conta conta) {
        this.tipo = tipo;
        this.valor = valor;
        this.conta = conta;
        this.dataTransacao = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Tipo da transacao : " + tipo + "|Valor: " + valor + "|Data : " + dataTransacao.
                format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}
