package br.com.megasoftgyn.projetoversao1.compra;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compra")
public class CompraApi {
    
    @Autowired
    private CompraService compraService;
    
    @GetMapping("/cpf/{cpf}")
    public CompraCadastroItemDto consultarCompraEspecifica(@PathVariable(value = "cpf") final String cpf) {
        return this.compraService.compraPessoa(cpf);
    }
    
    @GetMapping
    public List<CompraCadastroItemDto> consultarComprasEItens() {
        return this.compraService.comprasEItensCriteria();
    }
    
    @GetMapping("/bairro/{bairro}")
    public BigDecimal getTotalCompras(@PathVariable(value = "bairro") final String bairro) {
        return this.compraService.totalDeComprasPorBairro(bairro);
    }
    
    @GetMapping("/{bairro}")
    public Long quantidadeDeComprasPorBairro(@PathVariable(value = "bairro") final String bairro) {
        return this.compraService.quantidadeDeComprasPorBairro(bairro);
    }
    
    @PostMapping("/cadastrar")
    public Compra cadastrarCompra(@RequestBody final Compra compra) {
        return this.compraService.cadastrarCompra(compra);
    }
    
    @PostMapping("/cadastrar-itens")
    public Long cadastrarCompraItens(@RequestBody final CompraCadastroItemDto dto) {
        return this.compraService.cadastrarCompraItem(dto);
    }
}
