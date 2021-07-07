package br.com.megasoftgyn.projetoversao1.endereco;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/endereco")
public class EnderecoApi {
    
    @Autowired
    private EnderecoService enderecoService;
    
    @GetMapping(path = "/{numerodaPagina}")
    public List<EnderecoDto> listarEnderecos(@PathVariable(name = "numerodaPagina") final int numeroDaPagina) {
        return this.enderecoService.listarEnderecoDto(numeroDaPagina);
    }
    
    @PostMapping("/cadastrar")
    public Endereco cadastrarEndereco(@Valid @Validated @RequestBody final EnderecoDto endereco) throws IOException {
        return this.enderecoService.salvarEndereco(endereco);
    }
}
