package br.com.SistemaBancario.SistemaBancario.Model;

public interface IConverte {
    <T> T ObterDados(String json, Class<T> tClass);
}
