package br.com.megasoftgyn.projetoversao1.compra;

import java.math.BigDecimal;

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

import br.com.megasoftgyn.projetoversao1.pessoa.Pessoa;

@Entity
@Table(name = "compra")
public class Compra {
    
    @Id
    @SequenceGenerator(name = "seq_compra_id", sequenceName = "compra_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_compra_id")
    @Column(name = "compra_id", unique = true, nullable = false, updatable = false)
    private Long id;
    
    @JoinColumn(name = "pessoa", unique = true, nullable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    public Pessoa pessoa;
    
    @Column(name = "valor", unique = true, nullable = false, updatable = false)
    private BigDecimal valor;
    
    public Compra() {}
    
    public Compra(final Long id, final Pessoa pessoa, final BigDecimal valor) {
        this.id = id;
        this.pessoa = pessoa;
        this.valor = valor;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public BigDecimal getValor() {
        return this.valor;
    }
    
    public void setValor(final BigDecimal valor) {
        this.valor = valor;
    }
    
    public void setPessoa(final Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public Pessoa getPessoa() {
        return this.pessoa;
    }
}
