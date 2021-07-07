package br.com.megasoftgyn.projetoversao1.bairro;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.megasoftgyn.projetoversao1.excecao.ErroPadrao;
import br.com.megasoftgyn.projetoversao1.excecao.ExcecaoController;
import br.com.megasoftgyn.projetoversao1.pessoa.PessoaDto;

@CrossOrigin
@RestController
@RequestMapping("/bairro")
public class BairroApi {
    
    @Autowired
    private BairroService bairroService;
    
    @Autowired
    private ExcecaoController excecao;
    
    @GetMapping("/{nomebairro}")
    public List<PessoaDto> pessoasBairro(@PathVariable(value = "nomebairro") final String nomeBairro) {
        return this.bairroService.procurarBairroPessoa(nomeBairro);
    }
    
    @GetMapping
    public List<Bairro> getBairros() {
        return this.bairroService.listarBairros();
    }
    
    @GetMapping(path = "/paginado/{numerodaPagina}")
    public List<BairroDto> getBairrosPaginado(@PathVariable(name = "numerodaPagina") final int numeroDaPagina) {
        return this.bairroService.listarBairrosPaginado(numeroDaPagina);
    }
    
    @GetMapping("/iptu-juridico")
    public BigDecimal consultarSomaIptuJurid() {
        return this.bairroService.buscarSomaIptuJuridico();
    }
    
    @PostMapping("/cadastrar")
    public void cadastrarBairro(@Valid @RequestBody final BairroDto bairroDto) throws IOException {
        this.bairroService.salvarBairroDto(bairroDto);
    }
    
    // @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ErroPadrao> deletarBairro(@PathVariable final Long id) {
        return this.bairroService.deletarBairro(id);
    }
    
    // DTO
    @PutMapping(path = "/{id}")
    public void alterarBairro(@RequestBody final BairroDto bairroEditado, @PathVariable(name = "id") final Long id) throws IOException {
        this.bairroService.alterarBairro(bairroEditado, id);
    }
    
    @GetMapping("/nome")
    public List<String> retornaNomes() {
        return this.bairroService.retornaNomesDosBairros();
    }
}
