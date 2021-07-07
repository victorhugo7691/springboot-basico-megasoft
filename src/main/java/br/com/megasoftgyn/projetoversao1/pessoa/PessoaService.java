package br.com.megasoftgyn.projetoversao1.pessoa;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.megasoftgyn.projetoversao1.bairro.BairroRepository;
import br.com.megasoftgyn.projetoversao1.endereco.Endereco;
import br.com.megasoftgyn.projetoversao1.endereco.EnderecoService;
import br.com.megasoftgyn.projetoversao1.profissao.Profissao;
import br.com.megasoftgyn.projetoversao1.profissao.ProfissaoService;
import br.com.megasoftgyn.projetoversao1.utilidades.GeradorDeRelatorio;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;
    
    @Autowired
    private BairroRepository bairroRepository;
    
    @Autowired
    private EnderecoService enderecoService;
    
    @Autowired
    private GeradorDeRelatorio geradorDeRelatorio;
    
    @Autowired
    private ProfissaoService profissaoService;
    
    public List<PessoaCadastroDto> buscarPessoaDto(final int numeroDaPagina) {
        return this.converterPessoaCadastroDto(this.pessoaRepository.listarPessoasPaginado(numeroDaPagina));
    }
    
    @Transactional
    public void deletarPessoa(final String cpf) {
        this.pessoaRepository.deletePessoaCriteria(cpf);
    }
    
    public ResponseEntity<byte[]> imprime() {
        final Map<String, Object> parametro = new HashMap<>();
        parametro.put("titulo", "Relatório de Pessoas");
        final InputStream stream = this.getClass().getResourceAsStream("resources/Pessoa.jasper");
        final byte[] contents = this.geradorDeRelatorio.imprimirRelatorio(parametro, this.listarPessoaDto(), stream);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        // Here you have to set the actual filename of your pdf
        final String filename = "relatorio.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        final ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        return response;
        
    }
    
    public List<Pessoa> buscarPessoa() {
        return this.pessoaRepository.buscarPessoas();
    }
    
    public List<Pessoa> buscarPessoaNome() {
        return this.pessoaRepository.buscarNomePessoas();
    }
    
    public List<PessoaDto> converterPessoaDto(final List<Pessoa> pessoas) {
        final List<PessoaDto> pessoasDto = new ArrayList<>();
        for (final Pessoa pessoa : pessoas) {
            pessoasDto.add(new PessoaDto(pessoa));
        }
        return pessoasDto;
    }
    
    public List<PessoaCadastroDto> converterPessoaCadastroDto(final List<Pessoa> pessoas) {
        final List<PessoaCadastroDto> pessoaCadastroDto = new ArrayList<>();
        for (final Pessoa pessoa : pessoas) {
            pessoaCadastroDto.add(new PessoaCadastroDto(pessoa));
        }
        return pessoaCadastroDto;
    }
    
    public List<PessoaDto> listarPessoaDto() {
        return this.pessoaRepository.listarPessoaDto();
    }
    
    public List<PessoaDto> retornarDto() {
        return this.converterPessoaDto(this.buscarPessoa());
    }
    
    public List<PessoaDto> listarPessoaDeBairro(final String nome) {
        // return this.bairroRepository.buscarBairro(nome);
        return this.pessoaRepository.listarPessoaDeBairro(nome);
    }
    
    public List<PessoaDto> buscarPessoaProfissao(final Integer id) {
        return this.pessoaRepository.buscarPessoaProfissao(id);
    }
    
    public Long buscarQuantidadePessoaTipo(final int tipo, final String bairro) {
        return this.pessoaRepository.buscarQuantidadePessoaTipo(tipo, bairro);
    }
    
    // Em andamento
    @Transactional
    public String salvarPessoaDto(final PessoaCadastroDto dto) {
        Pessoa pessoa = new Pessoa();
        final Endereco endereco = this.enderecoService.buscarEnderecoComId(dto.getEndereco().getId());
        pessoa = dto.transformaDtoPesoa();
        if (dto.getEndereco().getId().equals(endereco.getId())) {
            pessoa.setEndereco(endereco);
        } else {
            pessoa.setEndereco(dto.getEndereco());
        }
        final Profissao profissao = this.profissaoService.buscarProfissaoComId(dto.getProfissao().getId());
        if (profissao.getId().equals(dto.getProfissao().getId())) {
            pessoa.setProfissao(profissao);
        } else {
            pessoa.setProfissao(dto.getProfissao());
        }
        this.pessoaRepository.salvarPessoa(pessoa);
        return pessoa.getCpf();
    }
}
