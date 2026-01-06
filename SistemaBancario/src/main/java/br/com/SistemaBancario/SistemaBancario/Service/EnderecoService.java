package br.com.SistemaBancario.SistemaBancario.Service;

import br.com.SistemaBancario.SistemaBancario.Dto.EnderecoDto;
import br.com.SistemaBancario.SistemaBancario.Model.Endereco;
import br.com.SistemaBancario.SistemaBancario.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository EnderecoRepository;

    public Optional<Endereco> buscarEnderecoPorId(Long id) {
        return EnderecoRepository.findById(id);
    }

    public List<EnderecoDto> obterTodosEnderecos(){
        return converteDados(EnderecoRepository.findAll());
    }

    public List<EnderecoDto> converteDados(List<Endereco> enderecos){
        return enderecos.stream()
                .map(e -> new EnderecoDto(e.getCidade(),e.getEstado(),e.getBairro(),
                        e.getLogradouro(),e.getId()))
                .collect(Collectors.toList());
    }

    public Optional<Endereco> alterarEndereco(Long id,Endereco novoEndereco) {
        Endereco endereco = EnderecoRepository.findById(id).orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND,"conta nao encontrada"));

        endereco.setEstado(novoEndereco.getEstado());
        endereco.setBairro(novoEndereco.getBairro());
        endereco.setCidade(novoEndereco.getCidade());
        endereco.setLogradouro(novoEndereco.getLogradouro());

        return Optional.of(EnderecoRepository.save(endereco));

    }
}