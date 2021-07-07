package br.com.megasoftgyn.projetoversao1.pessoa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.megasoftgyn.projetoversao1.endereco.Endereco;
import br.com.megasoftgyn.projetoversao1.profissao.Profissao;
import br.com.megasoftgyn.projetoversao1.tipopessoa.TipoPessoaEnum;

@Entity
@Table(name = "pessoa")
public class Pessoa {
    
    @Id
    @Column(name = "cpf", unique = true, nullable = false, updatable = false)
    private String cpf;
    
    @Column(name = "tipo_pessoa", unique = true, nullable = false, updatable = false)
    private int tipoPessoa;
    
    /*
     * @Column(name = "endereco", unique = true, nullable = false, updatable = false) private Long endereco;
     */
    
    @JoinColumn(name = "endereco", unique = true, nullable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    public Endereco endereco;
    
    /*
     * @Column(name = "profissao", unique = true, nullable = false, updatable = false) private Long profissao;
     */
    
    @JoinColumn(name = "profissao", unique = true, nullable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    public Profissao profissao;
    
    @Column(name = "nome", unique = true, nullable = false, updatable = false)
    private String nome;
    
    @Column(name = "sobrenome", unique = true, nullable = false, updatable = false)
    private String sobrenome;
    
    @Column(name = "idade", unique = true, nullable = false, updatable = false)
    private Integer idade;
    
    @Column(name = "peso", unique = true, nullable = false, updatable = false)
    private Float peso;
    
    public Pessoa(final String cpf, final String nome, final String sobrenome,
            final int tipoPessoa, final Endereco endereco,
            final Profissao profissao, final Integer idade, final Float peso) {
        this.cpf = cpf;
        this.nome = nome;
        this.tipoPessoa = tipoPessoa;
        this.endereco = endereco;
        this.profissao = profissao;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.peso = peso;
    }
    
    public Pessoa() {}
    
    public String getCpf() {
        return this.cpf;
    }
    
    public void setCpf(final String cpf) {
        this.cpf = cpf;
    }
    
    public void setName(final String nome) {
        this.nome = nome;
    }
    
    /*
     * public TipoPessoaEnum getTipoPessoa() { return this.tipoPessoa; // this.tipoPessoa.getValor(); }
     */
    
    public int getTipoPessoa() {
        return this.tipoPessoa;
    }
    
    public void setTipoPessoa(final TipoPessoaEnum tipoPessoa) {
        this.tipoPessoa = tipoPessoa.getValor();
    }
    
    public Endereco getEndereco() {
        return this.endereco;
    }
    
    public void setEndereco(final Endereco endereco) {
        this.endereco = endereco;
    }
    
    public Profissao getProfissao() {
        return this.profissao;
    }
    
    public void setProfissao(final Profissao profissao) {
        this.profissao = profissao;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(final String nome) {
        this.nome = nome;
    }
    
    public String getSobrenome() {
        return this.sobrenome;
    }
    
    public void setSobrenome(final String sobrenome) {
        this.sobrenome = sobrenome;
    }
    
    public Integer getIdade() {
        return this.idade;
    }
    
    public void setIdade(final Integer idade) {
        this.idade = idade;
    }
    
    public Float getPeso() {
        return this.peso;
    }
    
    public void setPeso(final Float peso) {
        this.peso = peso;
    }
}
