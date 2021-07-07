package br.com.megasoftgyn.projetoversao1.itemcompra;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemCompraApi {
    
    @Autowired
    private ItemCompraService itemCompraService;
    
    @GetMapping
    public List<ItemDeCompraDto> listarItens() {
        return this.itemCompraService.listarItensDeCompraDto();
    }
    
    @GetMapping("/consultar-id/{id}")
    public ItemDeCompraDto consultarItensId(@PathVariable(name = "id") final int id) {
        return this.itemCompraService.listarItemPorId(id);
    }
    
    @GetMapping("/consultar-nome/{nome}")
    public List<ItemDeCompraDto> consultarItemNome(@PathVariable(name = "nome") final String nome) {
        return this.itemCompraService.consultarItemNome(nome);
    }
    
    @GetMapping("/pagina/{id}")
    public List<ItemDeCompra> consultarItemPagina(@PathVariable(name = "id") final int id) {
        return this.itemCompraService.consultarItemPagina(id);
    }
    
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void alterarItemCompra(@Valid @RequestBody final ItemDeCompra item) {
        this.itemCompraService.alterarItem(item);
    }
    
}
