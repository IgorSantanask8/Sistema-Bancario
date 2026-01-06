package br.com.SistemaBancario.SistemaBancario.controller;

import br.com.SistemaBancario.SistemaBancario.Dto.ContaDto;
import br.com.SistemaBancario.SistemaBancario.Model.Dados_Conta;
import br.com.SistemaBancario.SistemaBancario.Service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService service;

    @GetMapping
    public List<ContaDto> obterContas(){
        return service.obterContas();
    }
    
    @GetMapping("/{id}")
    public Optional<Dados_Conta> obterPorId(@PathVariable Long id){
        return service.obterPorId(id);
    }

    @PostMapping
    public ResponseEntity<Dados_Conta> criarConta(@RequestBody Dados_Conta conta){
        Dados_Conta novaConta = service.salvar(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConta(@PathVariable Long id){
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/renda")
    public ResponseEntity<Optional<Dados_Conta>> atualizarRenda(@PathVariable Long id, @RequestBody Double novaRenda){
        Optional<Dados_Conta> contaAtualizada = service.atualizarRenda(id,novaRenda);
        return ResponseEntity.ok(contaAtualizada);
    }

    @PutMapping("/{id}/depositar")
    public ResponseEntity<Optional<Dados_Conta>> depositarNaConta(@PathVariable Long id, @RequestBody Double novoDeposito){
        Optional<Dados_Conta> depositoConta = service.depositarNaConta(id, novoDeposito);
        return ResponseEntity.ok(depositoConta);
    }

    @PutMapping("/{id}/sacar")
    public ResponseEntity<Optional<Dados_Conta>> sacarNaConta(@PathVariable Long id, @RequestBody Double novoSaque){
        Optional<Dados_Conta> saqueConta = service.sacarNaConta(id, novoSaque);
        return ResponseEntity.ok(saqueConta);
    }
}
