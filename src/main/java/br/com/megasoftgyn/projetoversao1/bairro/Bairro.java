package br.com.megasoftgyn.projetoversao1.bairro;

import java.io.IOException;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "bairro")
public class Bairro {
    
    @Id
    @SequenceGenerator(name = "seq_bairro_id", sequenceName = "bairro_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bairro_id")
    @Column(name = "bairro_id", unique = true, nullable = false, updatable = false)
    private Long id;
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "valor_iptu", nullable = false)
    private BigDecimal valorDoIptu;
    
    public Bairro() {}
    
    public Bairro(final String nome, final BigDecimal valorDoIptu) {
        this.nome = nome;
        this.valorDoIptu = valorDoIptu;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(final String nome) throws IOException {
        if (!nome.equals(null) && nome.length() >= 4) {
            this.nome = nome;
        } else {
            throw new IOException("Impossível adicionar com o campo nome vázio!");
        }
    }
    
    public BigDecimal getValorDoIptu() {
        return this.valorDoIptu;
    }
    
    public void setValorDoIptu(final BigDecimal valorDoIptu) throws IOException {
        if (valorDoIptu != null) {
            this.valorDoIptu = valorDoIptu;
        } else {
            throw new IOException("Impossível adicionar com o campo valor do IPTU vázio!");
        }
    }
}
