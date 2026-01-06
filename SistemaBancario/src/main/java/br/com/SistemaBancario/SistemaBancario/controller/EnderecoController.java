package br.com.SistemaBancario.SistemaBancario.controller;

import br.com.SistemaBancario.SistemaBancario.Dto.EnderecoDto;
import br.com.SistemaBancario.SistemaBancario.Model.Endereco;
import br.com.SistemaBancario.SistemaBancario.Service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping("/enderecos/{id}")
    public ResponseEntity<Optional<Endereco>> buscarEnderecos(@PathVariable Long id){
        Optional<Endereco> enderecoAtual = service.buscarEnderecoPorId(id);

        if(enderecoAtual.isEmpty()){
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(enderecoAtual);
    }
    @GetMapping("/enderecos")
    public List<EnderecoDto> obterTodosEnderecos(){
        return service.obterTodosEnderecos();
    }
}
