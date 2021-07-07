package br.com.megasoftgyn.projetoversao1.endereco;

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

import br.com.megasoftgyn.projetoversao1.bairro.Bairro;

@Entity
@Table(name = "endereco")
public class Endereco {
    
    @Id
    @SequenceGenerator(name = "seq_endereco_id", sequenceName = "endereco_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_endereco_id")
    @Column(name = "endereco_id", unique = true, nullable = false, updatable = false)
    private Long id;
    
    @Column(name = "rua", nullable = false)
    private String rua;
    
    @Column(name = "numero", nullable = false)
    private Integer numero;
    
    @JoinColumn(name = "bairro")
    @ManyToOne(fetch = FetchType.LAZY)
    public Bairro bairro;
    
    @Column(name = "cep", nullable = false)
    private String cep;
    
    public Endereco(final Bairro bairro) {
        this.bairro = bairro;
    }
    
    public Endereco() {}
    
    public Endereco(final String rua, final Integer numero, final Bairro bairro, final String cep) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public String getRua() {
        return this.rua;
    }
    
    public void setRua(final String rua) {
        this.rua = rua;
    }
    
    public Integer getNumero() {
        return this.numero;
    }
    
    public void setNumero(final Integer numero) {
        this.numero = numero;
    }
    
    public Bairro getBairro() {
        return this.bairro;
    }
    
    public void setBairro(final Bairro bairro) {
        this.bairro = bairro;
    }
    
    public String getCep() {
        return this.cep;
    }
    
    public void setCep(final String cep) {
        this.cep = cep;
    }
    
}
