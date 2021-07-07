package br.com.megasoftgyn.projetoversao1.itemcompra;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megasoftgyn.projetoversao1.compra.Compra;
import br.com.megasoftgyn.projetoversao1.compra.CompraCadastroItemDto;
import br.com.megasoftgyn.projetoversao1.compra.CompraRepository;

@Service
public class ItemCompraService {
    
    @Autowired
    private ItemCompraRepository itemCompraRepository;
    
    @Autowired
    private CompraRepository compraService;
    
    public List<ItemDeCompra> consultarItemId(final int id) {
        return this.itemCompraRepository.consultarItemCompraId(id);
    }
    
    public ItemDeCompraDto listarItemPorId(final int id) {
        return this.itemCompraRepository.consultarItemPorId(id);
    }
    
    public List<ItemDeCompraDto> listarItensDeCompraDto() {
        return this.itemCompraRepository.listarItensDeCompra();
    }
    
    public List<ItemDeCompraDto> consultarTodosItens() {
        return this.converterItemDeCompraParaDto(this.itemCompraRepository.consultarTodosItensCompra());
    }
    
    public List<ItemDeCompra> consultarItens() {
        return this.itemCompraRepository.consultarTodosItensCompra();
    }
    
    public List<ItemDeCompraDto> consultarItemNome(final String nome) {
        return this.itemCompraRepository.listarItemPorNome(nome);
    }
    
    public List<ItemDeCompra> consultarItemPagina(final int id) {
        return this.itemCompraRepository.consultarItemCompraPagina(id);
    }
    
    public List<ItemDeCompraDto> converterItemDeCompraParaDto(final List<ItemDeCompra> itens) {
        final List<ItemDeCompraDto> itensDto = new ArrayList<>();
        for (final ItemDeCompra item : itens) {
            itensDto.add(new ItemDeCompraDto(item));
        }
        return itensDto;
    }
    
    @Transactional
    public Long cadastrarItem(final CompraCadastroItemDto dto) {
        Compra compra = new Compra();
        ItemDeCompra item = new ItemDeCompra();
        compra = this.compraService.salvarCompra(dto.converterDtoCompra());
        /*
         * item.setCompra(compra.getId()); item.setNomeItem(dto.getNomeItem()); item.setValor(dto.getValorItem());
         * 
         * this.itemCompraRepository.salvarItemCompra(item); return dto.getCompraId().getId();
         */
        item = this.itemCompraRepository.salvarItemCompra(dto.converterDtoItem());
        return item.getId();
    }
    
    @Transactional
    public void alterarItem(final ItemDeCompra item) {
        final ItemDeCompra itemComp = new ItemDeCompra();
        itemComp.setCompra(item.getCompra());
        itemComp.setId(item.getId());
        itemComp.setNomeItem(item.getNomeItem());
        itemComp.setValor(item.getValor());
        this.itemCompraRepository.alterarItemCompra(itemComp);
    }
}
