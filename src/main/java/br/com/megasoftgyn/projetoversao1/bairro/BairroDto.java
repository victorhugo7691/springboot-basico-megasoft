package br.com.megasoftgyn.projetoversao1.bairro;

import java.io.IOException;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BairroDto {
    
    private final Long id;
    
    @NotBlank(message = "O nome não pode ser vazio")
    @Size(min = 4, message = "O nome deve ter ao menos 4 caracteres")
    private final String nome;
    
    @NotNull(message = "O valor do IPTU não pode ser vazio")
    private final BigDecimal valorDoIptu;
    
    public BairroDto() {
        this.id = null;
        this.nome = "";
        this.valorDoIptu = null;
    };
    
    public BairroDto(final Bairro bairro) {
        this.id = bairro.getId();
        this.nome = bairro.getNome();
        this.valorDoIptu = bairro.getValorDoIptu();
    }
    
    public BairroDto(final Long id, final String nome, final BigDecimal valorDoIptu) {
        this.id = id;
        this.nome = nome;
        this.valorDoIptu = valorDoIptu;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public BigDecimal getValorDoIptu() {
        return this.valorDoIptu;
    }
    
    public Bairro converteDtoBairro() throws IOException {
        final Bairro bairro = new Bairro();
        bairro.setNome(this.getNome());
        bairro.setValorDoIptu(this.getValorDoIptu());
        return bairro;
    }
}
