package br.com.megasoftgyn.projetoversao1.profissao;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "profissao")
public class Profissao {
    
    @Id
    @SequenceGenerator(name = "seq_profissao_id", sequenceName = "profissao_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_profissao_id")
    @Column(name = "profissao_id", unique = true, nullable = false, updatable = false)
    private Long id;
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "salario", nullable = false)
    private BigDecimal salario;
    
    /*
     * @OneToMany(fetch = FetchType.LAZY, mappedBy = "profissaoPessoa") private List<Pessoa> pessoa;
     */
    
    public Profissao() {}
    
    public Profissao(final String nome, final BigDecimal salario) {
        this.nome = nome;
        this.salario = salario;
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
    
    public void setNome(final String nome) {
        this.nome = nome;
    }
    
    public BigDecimal getSalario() {
        return this.salario;
    }
    
    public void setSalario(final BigDecimal salario) {
        this.salario = salario;
    }
    
}
