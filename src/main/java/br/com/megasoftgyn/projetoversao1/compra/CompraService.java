package br.com.megasoftgyn.projetoversao1.compra;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megasoftgyn.projetoversao1.itemcompra.ItemCompraService;
import br.com.megasoftgyn.projetoversao1.itemcompra.ItemDeCompra;
import br.com.megasoftgyn.projetoversao1.pessoa.Pessoa;
import br.com.megasoftgyn.projetoversao1.pessoa.PessoaService;

@Service
public class CompraService {
    
    @Autowired
    private CompraRepository compraRepository;
    
    @Autowired
    private ItemCompraService itemCompraService;
    
    @Autowired
    private PessoaService pessoaService;
    
    public CompraCadastroItemDto compraPessoa(final String cpf) {
        return this.compraRepository.buscarCompEspecifCriteria(cpf);
    }
    
    public List<CompraCadastroItemDto> compras() {
        return this.converterCompraEItemDto(this.compraRepository.buscarTodasComp(), this.itemCompraService.consultarItens(), this.pessoaService.buscarPessoa());
    }
    
    public List<CompraCadastroItemDto> comprasEItensCriteria() {
        return this.compraRepository.listarComprasEItensCriteria();
    }
    
    public List<CompraCadastroItemDto> converterCompraEItemDto(final List<Compra> compras, final List<ItemDeCompra> itens, final List<Pessoa> pessoas) {
        final List<CompraCadastroItemDto> compraItensDto = new ArrayList<>();
        for (final Compra compra : compras) {
            compraItensDto.add(new CompraCadastroItemDto(compra));
        }
        return compraItensDto;
    }
    
    public Long quantidadeDeComprasPorBairro(final String bairro) {
        return this.compraRepository.quantidadeDeCompraPorBairro(bairro);
    }
    
    public BigDecimal totalDeComprasPorBairro(final String bairro) {
        return this.compraRepository.totalDeComprasDeBairro(bairro);
    }
    
    @Transactional
    public Compra cadastrarCompra(final Compra compra) {
        return this.compraRepository.salvarCompra(compra);
    }
    
    @Transactional
    public Long cadastrarCompraItem(final CompraCadastroItemDto dto) {
        return this.itemCompraService.cadastrarItem(dto);
    }
}
