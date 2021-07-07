package br.com.megasoftgyn.projetoversao1.pessoa;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.megasoftgyn.projetoversao1.endereco.Endereco;
import br.com.megasoftgyn.projetoversao1.profissao.Profissao;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PessoaCadastroDto {
    
    @NotNull(message = "Informe o CPF")
    private String cpf;
    
    @NotBlank(message = "O nome não pode ser vazio")
    private String nomePessoa;
    
    @NotBlank(message = "O sobrenome não pode ser vazio")
    private String sobrenome;
    
    @NotNull(message = "O campo idade deve ser preenchido")
    private Integer idade;
    
    @NotNull(message = "Deve ser informado o tipo de pessoa")
    private int tipoPessoaId;
    
    @NotNull(message = "Informe o endereço")
    private Endereco endereco;
    
    @NotNull(message = "Informe a profissão")
    private Profissao profissao;
    
    @NotNull(message = "O peso deve ser informado")
    private Float peso;
    
    public PessoaCadastroDto(final String cpf, final int tipoPessoaId, final Endereco endereco,
            final Profissao profissao, final String nomePessoa, final String sobrenome, final Integer idade,
            final Float peso) {
        super();
        this.cpf = cpf;
        this.tipoPessoaId = tipoPessoaId;
        this.endereco = endereco;
        this.profissao = profissao;
        this.nomePessoa = nomePessoa;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.peso = peso;
    }
    
    public PessoaCadastroDto() {}
    
    public PessoaCadastroDto(final Pessoa pessoa) {
        this.cpf = pessoa.getCpf();
        this.tipoPessoaId = pessoa.getTipoPessoa();
        this.endereco = pessoa.getEndereco();
        this.profissao = pessoa.getProfissao();
        this.nomePessoa = pessoa.getNome();
        this.sobrenome = pessoa.getSobrenome();
        this.idade = pessoa.getIdade();
        this.peso = pessoa.getPeso();
        
    }
    
    public String getCpf() {
        return this.cpf;
    }
    
    public int getTipoPessoaId() {
        return this.tipoPessoaId;
    }
    
    public Endereco getEndereco() {
        return this.endereco;
    }
    
    public Profissao getProfissao() {
        return this.profissao;
    }
    
    public String getNome() {
        return this.nomePessoa;
    }
    
    public String getSobrenome() {
        return this.sobrenome;
    }
    
    public Integer getIdade() {
        return this.idade;
    }
    
    public Float getPeso() {
        return this.peso;
    }
    
    public Pessoa transformaDtoPesoa() {
        return new Pessoa(this.cpf, this.nomePessoa, this.sobrenome, this.tipoPessoaId, this.endereco, this.profissao, this.idade, this.peso);
    }
    
    // ---------------Métodos para conversão de classes---------------------
    /*
     * public Pessoa converterDtoPessoa() { //return new Pessoa(this.cpf, this.nomePessoa, this.sobrenome, this.tipoPessoaId, this.endereco.getId(), this.profissaoId, this.idade, this.peso);
     * 
     * }
     */
    
    /*
     * public Endereco converterDtoEndereco() { return new Endereco(this.rua, this.numero, this.bairroId, this.cep); }
     */
    /*
     * public EnderecoDto converterDtoEnderecoDto() { return new EnderecoDto(this.rua, this.numero, this.bairro, this.cep, this.nomeBairro, this.valorIptu); }
     */
    
    /*
     * public Bairro converterDtoBairro() { return new Bairro(this.nomeBairro, this.valorIptu); }
     */
    
}
