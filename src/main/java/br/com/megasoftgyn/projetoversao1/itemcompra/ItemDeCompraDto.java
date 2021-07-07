package br.com.megasoftgyn.projetoversao1.itemcompra;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.megasoftgyn.projetoversao1.compra.Compra;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDeCompraDto {
    
    private Long id;
    
    private Compra compra;
    
    private String nomeDoItem;
    
    private Float valor;
    
    public ItemDeCompraDto() {}
    
    public ItemDeCompraDto(final String nomeItem) {
        this.nomeDoItem = nomeItem;
    }
    
    public ItemDeCompraDto(final Long id, final Compra compra, final String nomeDoItem, final Float valor) {
        super();
        this.id = id;
        this.compra = compra;
        this.nomeDoItem = nomeDoItem;
        this.valor = valor;
    }
    
    public ItemDeCompraDto(final ItemDeCompra itemDeCompra) {
        this.id = itemDeCompra.getId();
        this.compra = itemDeCompra.getCompra();
        this.nomeDoItem = itemDeCompra.getNomeItem();
        this.valor = itemDeCompra.getValor();
    }
    
    public Long getId() {
        return this.id;
    }
    
    public Compra getCompra() {
        return this.compra;
    }
    
    public String getNomeDoItem() {
        return this.nomeDoItem;
    }
    
    public Float getValor() {
        return this.valor;
    }
    
    public ItemDeCompra converterDtoItemDeCompra() {
        return new ItemDeCompra(this.id, this.compra, this.nomeDoItem, this.valor);
    }
}
