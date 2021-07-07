package br.com.megasoftgyn.projetoversao1.compra;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.megasoftgyn.projetoversao1.itemcompra.ItemDeCompra;
import br.com.megasoftgyn.projetoversao1.pessoa.Pessoa;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompraCadastroItemDto {
    
    private Long id;
    
    private ItemDeCompra item;
    
    private Pessoa pessoa;
    
    private BigDecimal valorDaCompra;
    
    public CompraCadastroItemDto() {}
    
    public CompraCadastroItemDto(final Compra compra) {
        this.id = compra.getId();
        this.pessoa = compra.getPessoa();
        this.valorDaCompra = compra.getValor();
    }
    
    public CompraCadastroItemDto(final Compra compra, final ItemDeCompra item) {
        this.id = compra.getId();
        this.item = item;
        this.pessoa = compra.getPessoa();
        this.valorDaCompra = compra.getValor();
    }
    
    public CompraCadastroItemDto(final ItemDeCompra item, final Pessoa pessoa, final BigDecimal valorDaCompra) {
        this.item = item;
        this.pessoa = pessoa;
        this.valorDaCompra = valorDaCompra;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public ItemDeCompra getItem() {
        return this.item;
    }
    
    public Pessoa getPessoa() {
        return this.pessoa;
    }
    
    public BigDecimal getValorDaCompra() {
        return this.valorDaCompra;
    }
    
    public Compra converterDtoCompra() {
        return new Compra(this.id, this.pessoa, this.valorDaCompra);
    }
    
    public ItemDeCompra converterDtoItem() {
        return new ItemDeCompra(this.item.getId(), this.item.getCompra(), this.item.getNomeItem(), this.item.getValor());
    }
    
    public Pessoa converterDtoPessoa() {
        return new Pessoa(this.pessoa.getCpf(), this.pessoa.getNome(), this.pessoa.getSobrenome(), this.pessoa.getTipoPessoa(), this.pessoa.getEndereco(), this.pessoa.profissao,
                this.pessoa.getIdade(), this.pessoa.getPeso());
    }
}
