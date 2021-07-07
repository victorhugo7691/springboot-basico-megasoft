package br.com.megasoftgyn.projetoversao1.itemcompra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.megasoftgyn.projetoversao1.compra.Compra;
import br.com.megasoftgyn.projetoversao1.compra.Compra_;
import br.com.megasoftgyn.projetoversao1.pessoa.Pessoa;
import br.com.megasoftgyn.projetoversao1.pessoa.Pessoa_;

@Repository
public class ItemCompraRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public List<ItemDeCompraDto> listarItensDeCompra() {
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<ItemDeCompraDto> query = builder.createQuery(ItemDeCompraDto.class);
        final Root<ItemDeCompra> item = query.from(ItemDeCompra.class);
        query.multiselect(item);
        final TypedQuery<ItemDeCompraDto> typedQuery = this.entityManager.createQuery(query);
        try {
            return typedQuery.getResultList();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ItemDeCompraDto consultarItemPorId(final int id) {
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<ItemDeCompraDto> query = builder.createQuery(ItemDeCompraDto.class);
        final Root<ItemDeCompra> item = query.from(ItemDeCompra.class);
        query.multiselect(item);
        query.where(builder.equal(item.get(ItemDeCompra_.id), id));
        final TypedQuery<ItemDeCompraDto> typedQuery = this.entityManager.createQuery(query);
        try {
            return typedQuery.getSingleResult();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<ItemDeCompraDto> listarItemPorNome(final String nome) {
        // "SELECT i FROM ItemDeCompra i JOIN Pessoa p ON p.nome= :nome JOIN Compra c ON c.cpfCnpj=p.cpf AND c.id=i.compra";
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<ItemDeCompraDto> query = builder.createQuery(ItemDeCompraDto.class);
        final Root<ItemDeCompra> item = query.from(ItemDeCompra.class);
        final Join<ItemDeCompra, Compra> compra = item.join(ItemDeCompra_.compra);
        final Join<Compra, Pessoa> pessoa = compra.join(Compra_.pessoa);
        final Predicate compraItem = builder.equal(compra.get(Compra_.id), item.get(ItemDeCompra_.compra));
        final Predicate compraPessoa = builder.equal(pessoa.get(Pessoa_.nome), nome);
        query.multiselect(item);
        query.where(compraItem, compraPessoa);
        final TypedQuery<ItemDeCompraDto> typedQuery = this.entityManager.createQuery(query);
        try {
            return typedQuery.getResultList();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<ItemDeCompra> consultarTodosItensCompra() {
        final String jpql = "SELECT c FROM ItemDeCompra c";
        final TypedQuery<ItemDeCompra> typedQuery = this.entityManager.createQuery(jpql, ItemDeCompra.class);
        return typedQuery.getResultList();
    }
    
    public List<ItemDeCompra> consultarItemCompraId(final int id) {
        final String jpql = "SELECT c FROM ItemDeCompra c WHERE c.id= :id";
        final TypedQuery<ItemDeCompra> typedQuery = this.entityManager.createQuery(jpql, ItemDeCompra.class);
        typedQuery.setParameter("id", id);
        try {
            return typedQuery.getResultList();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<ItemDeCompra> consultarItemCompraNome(final String nome) {
        final String jpql = "SELECT i FROM ItemDeCompra i JOIN Pessoa p ON p.nome= :nome JOIN Compra c ON c.cpfCnpj=p.cpf AND c.id=i.compra";
        final TypedQuery<ItemDeCompra> typedQuery = this.entityManager.createQuery(jpql, ItemDeCompra.class);
        typedQuery.setParameter("nome", nome);
        try {
            return typedQuery.getResultList();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<ItemDeCompra> consultarItemCompraPagina(final int id) {
        final String jpql = "SELECT c FROM ItemDeCompra c WHERE c.id= :id ";
        final TypedQuery<ItemDeCompra> typedQuery = this.entityManager.createQuery(jpql, ItemDeCompra.class).setMaxResults(10);
        typedQuery.setParameter("id", id);
        try {
            return typedQuery.getResultList();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ItemDeCompra salvarItemCompra(final ItemDeCompra itemCompra) {
        this.entityManager.persist(itemCompra);
        return itemCompra;
    }
    
    @Transactional
    public void alterarItemCompra(final ItemDeCompra item) {
        this.entityManager.merge(item);
    }
}
