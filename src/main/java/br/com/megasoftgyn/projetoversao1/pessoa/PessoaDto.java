package br.com.megasoftgyn.projetoversao1.pessoa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PessoaDto {
    
    private final String nome;
    
    private String sobrenome;
    
    private final String cpf;
    
    private Integer idade;
    
    public PessoaDto(final Pessoa pessoa) {
        this.nome = pessoa.getNome();
        this.cpf = pessoa.getCpf();
        this.sobrenome = pessoa.getSobrenome();
        this.idade = pessoa.getIdade();
    }
    
    public PessoaDto(final String nome, final String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
    
    public PessoaDto(final String nome, final String sobrenome, final String cpf, final Integer idade) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.idade = idade;
    }
    
    public String getCpf() {
        return this.cpf;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public String getSobrenome() {
        return this.sobrenome;
    }
    
    public Integer getIdade() {
        return this.idade;
    }
}
