package br.com.megasoftgyn.projetoversao1.endereco;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.megasoftgyn.projetoversao1.bairro.Bairro;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoDto {
    
    private Long id;
    
    @NotNull(message = "O nome da rua não pode ser vazio")
    private String rua;
    
    @NotNull(message = "Digite o número 0 caso não tenha número")
    private Integer numero;
    
    @NotNull(message = "O cep não pode ser vazio")
    @Size(min = 7, max = 7, message = "O CEP deve conter 7 caracteres")
    private String cep;
    
    private Bairro bairro;
    
    public EnderecoDto(final Endereco endereco) {
        this.id = endereco.getId();
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.cep = endereco.getCep();
        this.bairro = endereco.getBairro();
    }
    
    public EnderecoDto(final Long id, final Bairro bairro, final Integer numero, final String cep, final String rua) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
    }
    
    public EnderecoDto() {}
    
    public String getRua() {
        return this.rua;
    }
    
    public Integer getNumero() {
        return this.numero;
    }
    
    public Bairro getBairro() {
        return this.bairro;
    }
    
    public String getCep() {
        return this.cep;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public Endereco transformaDtoEndereco() {
        return new Endereco(this.rua, this.numero, this.bairro, this.cep);
    }
    
    public Bairro transformaDtoBairro() {
        return new Bairro(this.bairro.getNome(), this.bairro.getValorDoIptu());
    }
    
}
