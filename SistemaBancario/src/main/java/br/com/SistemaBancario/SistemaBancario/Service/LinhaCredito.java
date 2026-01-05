package br.com.SistemaBancario.SistemaBancario.Service;

import jakarta.persistence.*;

@Entity
@Table(name = "linhas")
public class LinhaCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String tipo; //basico, premium ou ultra
    private double limite;
    @OneToOne
    @JoinColumn(name = "contas_id")
    private Dados_Conta contaLimite;

    public LinhaCredito(){}

    public LinhaCredito(String tipo, double limite, Dados_Conta contaLimite){
        this.tipo = tipo;
        this.limite = limite;
        this.contaLimite = contaLimite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Dados_Conta getContaLimite() {
        return contaLimite;
    }

    public void setContaLimite(Dados_Conta contaLimite) {
        this.contaLimite = contaLimite;
    }

    @Override
    public String toString() {
        return "Tipo da linha : " + tipo + "|Limite da conta : " + limite;
    }
}
