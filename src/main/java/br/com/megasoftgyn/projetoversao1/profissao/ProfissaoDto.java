package br.com.megasoftgyn.projetoversao1.profissao;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfissaoDto {
    
    private final Long id;
    
    @NotBlank(message = "O nome não pode ser vazio")
    private final String nome;
    
    @NotNull(message = "O salário não pode ser vazio")
    private final BigDecimal salario;
    
    public ProfissaoDto(final Profissao profissao) {
        this.id = profissao.getId();
        this.nome = profissao.getNome();
        this.salario = profissao.getSalario();
    }
    
    public ProfissaoDto(final Long id, final String nome, final BigDecimal salario) {
        super();
        this.id = id;
        this.nome = nome;
        this.salario = salario;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public BigDecimal getSalario() {
        return this.salario;
    }
    
}
