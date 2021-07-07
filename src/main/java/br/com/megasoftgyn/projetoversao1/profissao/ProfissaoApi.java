package br.com.megasoftgyn.projetoversao1.profissao;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/profissao")
public class ProfissaoApi {
    
    @Autowired
    private ProfissaoService profissaoService;
    
    /*
     * @GetMapping public List<Profissao> listarProfissao() { return this.profissaoService.listarProfissao(); }
     */
    
    @GetMapping
    public List<ProfissaoDto> listarProfissao() {
        return this.profissaoService.listarProfissao();
    }
    
    @PostMapping()
    public Profissao cadastrarProfissao(@Valid @RequestBody final Profissao profissao) {
        return this.profissaoService.inserirProfissao(profissao);
    }
    
    @DeleteMapping("/{id}")
    public void deletarProfissao(@PathVariable(name = "id") final int id) {
        this.profissaoService.deletarProfissao(id);
    }
    
}
