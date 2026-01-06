package br.com.SistemaBancario.SistemaBancario.Model;

import tools.jackson.databind.ObjectMapper;

public class Conversor implements IConverte {

    ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T ObterDados(String json, Class<T> tClass) {
        return mapper.readValue(json, tClass);
    }
}
