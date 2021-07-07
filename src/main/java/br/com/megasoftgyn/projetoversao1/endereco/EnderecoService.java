package br.com.megasoftgyn.projetoversao1.endereco;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megasoftgyn.projetoversao1.bairro.Bairro;
import br.com.megasoftgyn.projetoversao1.bairro.BairroService;

@Service
public class EnderecoService {
    
    @Autowired
    private EnderecoRepository enderecoRepository;
    
    @Autowired
    private BairroService bairroService;
    
    public List<EnderecoDto> listarEnderecoDto(final int numeroDaPagina) {
        return this.enderecoRepository.listarEnderecoDto(numeroDaPagina);
    }
    
    public List<EnderecoDto> listarEndereco(final int numeroDaPagina) {
        return this.converterEnderecoDto(this.enderecoRepository.listarEnderecoPaginado(numeroDaPagina));
    }
    
    public List<EnderecoDto> converterEnderecoDto(final List<Endereco> enderecos) {
        final List<EnderecoDto> enderecosDto = new ArrayList<>();
        for (final Endereco endereco : enderecos) {
            enderecosDto.add(new EnderecoDto(endereco));
        }
        return enderecosDto;
    }
    
    @Transactional
    public Endereco salvarEndereco(final EnderecoDto dto) throws IOException {
        Bairro bairro = new Bairro();
        bairro = dto.transformaDtoBairro();
        this.bairroService.salvarBairro(bairro);
        Endereco endereco = new Endereco();
        endereco = dto.transformaDtoEndereco();
        endereco.setBairro(bairro);
        this.enderecoRepository.salvarEndereco(endereco);
        return endereco;
    }
    
    public Endereco buscarEnderecoComId(final Long id) {
        return this.enderecoRepository.buscarEnderecoComId(id);
    }
}
