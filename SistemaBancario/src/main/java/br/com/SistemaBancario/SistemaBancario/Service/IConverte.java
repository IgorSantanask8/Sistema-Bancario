package br.com.SistemaBancario.SistemaBancario.Service;

public interface IConverte {
    <T> T ObterDados(String json, Class<T> tClass);
}
