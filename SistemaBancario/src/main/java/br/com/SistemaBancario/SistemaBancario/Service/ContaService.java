package br.com.SistemaBancario.SistemaBancario.Service;

import br.com.SistemaBancario.SistemaBancario.Dto.ContaDto;
import br.com.SistemaBancario.SistemaBancario.Model.Dados_Conta;
import br.com.SistemaBancario.SistemaBancario.Model.Transacao;
import br.com.SistemaBancario.SistemaBancario.repository.ContasRepository;
import br.com.SistemaBancario.SistemaBancario.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContaService {

    @Autowired
    private ContasRepository repository;
    @Autowired
    private TransacaoRepository Trepository;



   public List<ContaDto> converteDados(List<Dados_Conta> contas){
       return contas.stream()
               .map(c -> new ContaDto(c.getNome(),c.getCPF(),c.getRenda(),c.getSaldo(),c.getDataDeNascimento()))
               .collect(Collectors.toList());
   }

   public List<ContaDto> obterContas(){
       return converteDados(repository.findAll());
   }


    public Dados_Conta salvar(Dados_Conta conta) {
       if(conta.getSaldo() == null){
           conta.setSaldo(0.0);
       }
       return repository.save(conta);
    }

    public void excluir(Long id) {
       if(!repository.existsById(id)){
           throw new RuntimeException("Nao foi possivel deletar :conta nao encontrada");
       }
       repository.deleteById(id);
    }

    public Optional<Dados_Conta> obterPorId(Long id) {
       if(!repository.existsById(id)){
           throw new RuntimeException("Nao foi possivel encontrar a conta");
       }
       return repository.findById(id);
    }

    public Optional<Dados_Conta> atualizarRenda(Long id, Double novaRenda) {
       Dados_Conta conta = repository.findById(id).orElseThrow(() -> new RuntimeException("Nao foi possivel encontrar a conta"));
       conta.setRenda(novaRenda);

       return Optional.of(repository.save(conta));
    }

    public Optional<Dados_Conta> depositarNaConta(Long id, Double novoDeposito) {
       Dados_Conta conta = repository.findById(id).orElseThrow(() ->
               new ResponseStatusException(HttpStatus.NOT_FOUND, "conta inexistente"));
       conta.setSaldo(conta.getSaldo() + novoDeposito);

        Transacao transacao = new Transacao();
        transacao.setTipo("Deposito");
        transacao.setValor(novoDeposito);
        transacao.setConta(conta);

        Trepository.save(transacao);

       return Optional.of(repository.save(conta));
    }

    public Optional<Dados_Conta> sacarNaConta(Long id, Double novoSaque) {
       Dados_Conta conta = repository.findById(id).orElseThrow(() ->
               new ResponseStatusException(HttpStatus.NOT_FOUND,"conta inexistente"));
       conta.setSaldo(conta.getSaldo() - novoSaque);

       Transacao transacao = new Transacao();
       transacao.setTipo("Saque");
       transacao.setValor(novoSaque);
       transacao.setConta(conta);

       Trepository.save(transacao);
       return Optional.of(repository.save(conta));
    }
}
