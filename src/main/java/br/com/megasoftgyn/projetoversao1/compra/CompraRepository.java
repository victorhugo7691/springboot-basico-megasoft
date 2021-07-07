package br.com.megasoftgyn.projetoversao1.compra;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.stereotype.Repository;

import br.com.megasoftgyn.projetoversao1.bairro.Bairro;
import br.com.megasoftgyn.projetoversao1.bairro.Bairro_;
import br.com.megasoftgyn.projetoversao1.endereco.Endereco;
import br.com.megasoftgyn.projetoversao1.endereco.Endereco_;
import br.com.megasoftgyn.projetoversao1.itemcompra.ItemDeCompra;
import br.com.megasoftgyn.projetoversao1.itemcompra.ItemDeCompra_;
import br.com.megasoftgyn.projetoversao1.pessoa.Pessoa;
import br.com.megasoftgyn.projetoversao1.pessoa.Pessoa_;

@Repository
public class CompraRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public List<CompraCadastroItemDto> listarComprasEItensCriteria() {
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<CompraCadastroItemDto> query = builder.createQuery(CompraCadastroItemDto.class);
        // final Root<Compra> compra = query.from(Compra.class);
        final Root<ItemDeCompra> item = query.from(ItemDeCompra.class);
        final Join<ItemDeCompra, Compra> compra = item.join(ItemDeCompra_.compra);
        query.where(builder.equal(item.get(ItemDeCompra_.compra), compra.get(Compra_.id)));
        query.multiselect(compra);
        final TypedQuery<CompraCadastroItemDto> typedQuery = this.entityManager.createQuery(query);
        try {
            return typedQuery.getResultList();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Compra> buscarTodasComp() {
        final String jpql = "select c from Compra c";
        final Query query = this.entityManager.createQuery(jpql, Compra.class);
        try {
            return query.getResultList();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Compra buscarCompEspecif(final String cpf) {
        final String jpql = "select c from Compra c where c.pessoa.cpf = :cpf";
        final TypedQuery<Compra> typedQuery = this.entityManager.createQuery(jpql, Compra.class);
        typedQuery.setParameter("cpf", cpf);
        try {
            return typedQuery.getSingleResult();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    public CompraCadastroItemDto buscarCompEspecifCriteria(final String cpf) {
        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<CompraCadastroItemDto> criteriaQuery = criteriaBuilder.createQuery(CompraCadastroItemDto.class);
        final Root<Compra> compra = criteriaQuery.from(Compra.class);
        final Join<Compra, Pessoa> pessoa = compra.join(Compra_.pessoa);
        final Predicate predicate = criteriaBuilder.equal(pessoa.get(Pessoa_.cpf), cpf);
        criteriaQuery.multiselect(compra);
        criteriaQuery.where(predicate);
        try {
            return this.entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Long quantidadeDeCompraPorBairro(final String bairro) {
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> query = builder.createQuery(Long.class);
        final Root<Compra> compra = query.from(Compra.class);
        final Join<Compra, Pessoa> pessoa = compra.join(Compra_.pessoa);
        final Join<Pessoa, Endereco> endereco = pessoa.join(Pessoa_.endereco);
        final Join<Endereco, Bairro> bairroJoin = endereco.join(Endereco_.bairro);
        final Predicate nomeBairro = builder.equal(bairroJoin.get(Bairro_.nome), bairro);
        query.select(builder.count(compra));
        query.where(nomeBairro);
        final TypedQuery<Long> typedQuery = this.entityManager.createQuery(query);
        try {
            return typedQuery.getSingleResult();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public BigDecimal totalDeComprasDeBairro(final String bairro) {
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<BigDecimal> query = builder.createQuery(BigDecimal.class);
        final Root<Compra> compra = query.from(Compra.class);
        query.multiselect(builder.sum(compra.get(Compra_.valor)));
        final Join<Compra, Pessoa> pessoa = compra.join(Compra_.pessoa);
        final Join<Pessoa, Endereco> endereco = pessoa.join(Pessoa_.endereco);
        final Join<Endereco, Bairro> bairroJoin = endereco.join(Endereco_.bairro);
        query.where(builder.equal(bairroJoin.get(Bairro_.nome), bairro));
        final TypedQuery<BigDecimal> typedQuery = this.entityManager.createQuery(query);
        try {
            return typedQuery.getSingleResult();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Long buscarQuantPessoaBairro(final String bairro) {
        final String jpql = "SELECT COUNT(c)\n" +
                "FROM Compra c, Endereco e, Pessoa p\n" +
                "JOIN Bairro b\n" +
                "on b.nome= :bairro and e.bairro=b.id WHERE p.endereco=e.id";
        final TypedQuery<Long> typedQuery = this.entityManager.createQuery(jpql, Long.class);
        typedQuery.setParameter("bairro", bairro);
        try {
            return typedQuery.getSingleResult();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Long quantidadeDeBairro(final String bairro) {
        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        final Root<Compra> from = criteriaQuery.from(Compra.class);
        criteriaQuery.select(criteriaBuilder.count(from));
        final TypedQuery<Long> typedQuery = this.entityManager.createQuery(criteriaQuery);
        try {
            return typedQuery.getSingleResult();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Compra salvarCompra(final Compra compra) {
        this.entityManager.persist(compra);
        return compra;
    }
    
    private List<Selection<?>> camposCompraItemDto(final Root<Compra> fromCompra, final Root<ItemDeCompra> fromItem) {
        final List<Selection<?>> campos = new ArrayList<>();
        campos.add(fromCompra.get(Compra_.id));
        campos.add(fromItem.get(ItemDeCompra_.id));
        campos.add(fromCompra.get(Compra_.pessoa));
        campos.add(fromCompra.get(Compra_.valor));
        return campos;
    }
}
