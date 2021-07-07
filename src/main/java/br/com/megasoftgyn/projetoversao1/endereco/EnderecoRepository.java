package br.com.megasoftgyn.projetoversao1.endereco;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EnderecoRepository {
    
    @Autowired
    private EntityManager entityManager;
    
    public List<Endereco> listarEnderecos() {
        final String jpql = "SELECT e FROM Endereco e";
        final Query query = this.entityManager.createQuery(jpql, Endereco.class);
        return query.getResultList();
    }
    
    public List<EnderecoDto> listarEnderecoDto(final int numeroDaPagina) {
        final int tamanho = 8;
        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<EnderecoDto> criteriaQuery = criteriaBuilder.createQuery(EnderecoDto.class);
        final Root<Endereco> root = criteriaQuery.from(Endereco.class);
        criteriaQuery.multiselect(this.camposEndereco(root));
        final TypedQuery<EnderecoDto> typedQuery = this.entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult((numeroDaPagina - 1) * tamanho);
        typedQuery.setMaxResults(tamanho);
        try {
            return typedQuery.getResultList();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Endereco> listarEnderecoPaginado(final int numeroDaPagina) {
        final int tamanho = 8;
        final String jpql = "SELECT e FROM Endereco e";
        final TypedQuery<Endereco> typedQuery = this.entityManager.createQuery(jpql, Endereco.class);
        typedQuery.setFirstResult((numeroDaPagina - 1) * tamanho);
        typedQuery.setMaxResults(tamanho);
        try {
            return typedQuery.getResultList();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Endereco buscarEnderecoComId(final Long id) {
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Endereco> query = builder.createQuery(Endereco.class);
        final Root<Endereco> endereco = query.from(Endereco.class);
        query.where(builder.equal(endereco.get(Endereco_.id), id));
        query.select(endereco);
        final TypedQuery<Endereco> typedQuery = this.entityManager.createQuery(query);
        try {
            return typedQuery.getSingleResult();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Endereco salvarEndereco(final Endereco endereco) {
        this.entityManager.persist(endereco);
        return endereco;
    }
    
    private List<Selection<?>> camposEndereco(final Root<Endereco> from) {
        final List<Selection<?>> campos = new ArrayList<>();
        campos.add(from.get(Endereco_.id));
        campos.add(from.get(Endereco_.bairro));
        campos.add(from.get(Endereco_.numero));
        campos.add(from.get(Endereco_.cep));
        campos.add(from.get(Endereco_.rua));
        return campos;
    }
    
}
