package br.com.megasoftgyn.projetoversao1.bairro;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.megasoftgyn.projetoversao1.excecao.ErroPadrao;
import br.com.megasoftgyn.projetoversao1.excecao.ExcecaoController;
import br.com.megasoftgyn.projetoversao1.pessoa.PessoaDto;

@Service
public class BairroService {
    
    @Autowired
    private BairroRepository bairroRepository;
    
    @Autowired
    ExcecaoController excecao;
    
    public List<PessoaDto> procurarBairroPessoa(final String nome) {
        return this.bairroRepository.buscarBairro(nome);
    }
    
    public List<Bairro> listarBairros() {
        return this.bairroRepository.listarBairrosCriteria();
    }
    
    public List<BairroDto> listarBairrosPaginado(final int numeroDaPagina) {
        return this.converterBairroDto(this.bairroRepository.listarBairrosPaginadoCriteria(numeroDaPagina));
    }
    
    public List<BairroDto> converterBairroDto(final List<Bairro> bairros) {
        final List<BairroDto> bairrosDto = new ArrayList<>();
        for (final Bairro bairro : bairros) {
            bairrosDto.add(new BairroDto(bairro));
        }
        return bairrosDto;
    }
    
    public BigDecimal buscarSomaIptuJuridico() {
        return this.bairroRepository.calcularIptuJuridica();
    }
    
    @Transactional
    public void salvarBairroDto(final BairroDto bairroDto) throws IOException {
        this.bairroRepository.salvarBairro(bairroDto.converteDtoBairro());
    }
    
    public void salvarBairro(final Bairro bairro) throws IOException {
        this.bairroRepository.salvarBairro(bairro);
    }
    
    @Transactional
    public ResponseEntity<ErroPadrao> deletarBairro(final Long id) {
        try {
            this.bairroRepository.deleteBairroCriteria(id);
        } catch (final ObjectNotFoundException e) {
            return this.excecao.objectNotFound(e);
        }
        return null;
    }
    
    @Transactional
    public void alterarBairro(final BairroDto bairroEditado, final Long id) throws IOException {
        final Bairro bairro = this.bairroRepository.buscarBairroId(id);
        // bairro = bairroEditado.converteDtoBairro();
        bairro.setNome(bairroEditado.getNome());
        bairro.setValorDoIptu(bairroEditado.getValorDoIptu());
        this.bairroRepository.updateBairro(bairro, id);
    }
    
    public List<String> retornaNomesDosBairros() {
        return this.bairroRepository.nomesDosBairros();
    }
}
