package br.com.SistemaBancario.SistemaBancario.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public Dados_Conta getConta() {
        return conta;
    }

    public void setConta(Dados_Conta conta) {
        this.conta = conta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Tipo da transacao : " + tipo + "|Valor: " + valor + "|Data : " + dataTransacao.
                format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}
