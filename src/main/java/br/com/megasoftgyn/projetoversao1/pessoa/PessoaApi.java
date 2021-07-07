// Só métodos com informação do http (Interface de comunicação)
package br.com.megasoftgyn.projetoversao1.pessoa;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/pessoa")
public class PessoaApi {
    
    @Autowired
    private PessoaService pessoaService; // chamada de outra classe injeção de dependencia com spring(ele gerencia)
    
    @GetMapping("/dto")
    public List<PessoaDto> listarDto() {
        return this.pessoaService.retornarDto();
    }
    
    @GetMapping(path = "/{numerodaPagina}")
    public List<PessoaCadastroDto> listarPessoa(@PathVariable(name = "numerodaPagina") final int numeroDaPagina) {
        return this.pessoaService.buscarPessoaDto(numeroDaPagina);
    }
    
    @PostMapping("/cadastrar")
    public String cadastrarPessoaDto(@Valid @RequestBody final PessoaCadastroDto pessoa) {
        return this.pessoaService.salvarPessoaDto(pessoa);
    }
    
    @DeleteMapping("/{cpf}")
    public void deletarPessoa(@PathVariable(name = "cpf") final String cpf) {
        this.pessoaService.deletarPessoa(cpf);
    }
    
    @GetMapping("/imprimir")
    public ResponseEntity<byte[]> imprimir() {
        return this.pessoaService.imprime();
    }
    
    @GetMapping("/nome")
    public List<Pessoa> listarPessoasNome() {
        return this.pessoaService.buscarPessoaNome();
    }
    
    @GetMapping("/profissao/{id}")
    public List<PessoaDto> buscarPessoaProfissao(@PathVariable(name = "id") final Integer id) {
        return this.pessoaService.buscarPessoaProfissao(id);
    }
    
    @GetMapping("/quantidade-tipo/{tipo}/{bairro}")
    public Long consultarPessoaTipo(@PathVariable(name = "tipo") final int tipo, @PathVariable(name = "bairro") final String bairro) {
        return this.pessoaService.buscarQuantidadePessoaTipo(tipo, bairro);
    }
    
    @GetMapping("/bairro/{nomebairro}")
    public List<PessoaDto> consultarPessoaBairro(@PathVariable(name = "nomebairro") final String nome) {
        return this.pessoaService.listarPessoaDeBairro(nome);
    }
}
