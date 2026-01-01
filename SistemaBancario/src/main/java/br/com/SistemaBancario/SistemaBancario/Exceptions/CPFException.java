package br.com.SistemaBancario.SistemaBancario.Exceptions;

public class CPFException extends Exception{
    
    private String mensagem;

    public CPFException(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage() {
        return mensagem;
    }
}
