package br.com.megasoftgyn.projetoversao1.itemcompra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.megasoftgyn.projetoversao1.compra.Compra;

@Entity
@Table(name = "item_compra")
public class ItemDeCompra {
    
    @Id
    @SequenceGenerator(name = "seq_item_compra_id", sequenceName = "item_compra_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item_compra_id")
    @Column(name = "item_compra_id", unique = true, nullable = false, updatable = false)
    private Long id;
    
    @JoinColumn(name = "compra")
    @ManyToOne(fetch = FetchType.LAZY)
    private Compra compra;
    
    @Column(name = "nome_item", nullable = false)
    private String nomeItem;
    
    @Column(name = "valor", unique = true, nullable = false, updatable = false)
    private Float valor;
    
    public ItemDeCompra() {}
    
    public ItemDeCompra(final Long id, final Compra compra, final String nomeItem, final Float valor) {
        this.id = id;
        this.compra = compra;
        this.nomeItem = nomeItem;
        this.valor = valor;
    }
    
    public ItemDeCompra(final String nomeItem, final Float valorItem) {
        this.nomeItem = nomeItem;
        this.valor = valorItem;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public Compra getCompra() {
        return this.compra;
    }
    
    public void setCompra(final Compra compra) {
        this.compra = compra;
    }
    
    public String getNomeItem() {
        return this.nomeItem;
    }
    
    public void setNomeItem(final String nomeItem) {
        this.nomeItem = nomeItem;
    }
    
    public Float getValor() {
        return this.valor;
    }
    
    public void setValor(final Float valor) {
        this.valor = valor;
    }
    
}
