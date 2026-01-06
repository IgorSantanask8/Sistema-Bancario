package br.com.SistemaBancario.SistemaBancario.Service;

import br.com.SistemaBancario.SistemaBancario.Dto.ContaDto;
import br.com.SistemaBancario.SistemaBancario.Model.Transacao;
import br.com.SistemaBancario.SistemaBancario.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository Trepository;


    public List<Transacao> obterTransacoesPorConta(Long Contaid) {
        return Trepository.findByContaId(Contaid);
    }
}
