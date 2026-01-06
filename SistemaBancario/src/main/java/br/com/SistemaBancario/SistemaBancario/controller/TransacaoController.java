package br.com.SistemaBancario.SistemaBancario.controller;

import br.com.SistemaBancario.SistemaBancario.Model.Transacao;
import br.com.SistemaBancario.SistemaBancario.Service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @GetMapping("/contas/transacoes/{id}")
    public ResponseEntity<List<Transacao>> buscarTransacoes(@PathVariable Long id){
        List<Transacao> transacaoList = service.obterTransacoesPorConta(id);

        if(transacaoList.isEmpty()){
            return ResponseEntity.noContent().build();//Retorna 404(nao encontrado)
        }
        return ResponseEntity.ok(transacaoList);//Retorna 200(OK)
    }
}
