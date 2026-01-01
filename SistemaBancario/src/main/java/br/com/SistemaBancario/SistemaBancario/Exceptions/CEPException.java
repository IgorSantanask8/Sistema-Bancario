package br.com.SistemaBancario.SistemaBancario.Exceptions;

public class CEPException extends RuntimeException {

    private String mensagem;

    public CEPException(String mensagem) {
        this.mensagem = mensagem;
    }
    public String getMessage(){
        return mensagem;
    }
}
