package br.com.megasoftgyn.projetoversao1.profissao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfissaoService {
    
    @Autowired
    private ProfissaoRepository profissaoRepository;
    
    public List<ProfissaoDto> listarProfissao() {
        // return this.profissaoRepository.listarProfissoes();
        return this.profissaoRepository.listarProfissorsDto();
    }
    
    @Transactional
    public Profissao inserirProfissao(final Profissao profissao) {
        return this.profissaoRepository.salvarProfissao(profissao);
    }
    
    @Transactional
    public void deletarProfissao(final int id) {
        this.profissaoRepository.deleteProfissaoCriteria(id);
    }
    
    public Profissao buscarProfissaoComId(final Long id) {
        return this.profissaoRepository.buscarProfissaoComId(id);
    }
    
    public List<ProfissaoDto> converterProfissaoDto(final List<Profissao> profissoes) {
        final List<ProfissaoDto> profissoesDto = new ArrayList<>();
        for (final Profissao profissao : profissoes) {
            profissoesDto.add(new ProfissaoDto(profissao));
        }
        return profissoesDto;
    }
    
}
