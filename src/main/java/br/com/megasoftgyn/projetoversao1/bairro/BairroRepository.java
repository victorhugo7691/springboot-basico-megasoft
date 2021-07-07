package br.com.megasoftgyn.projetoversao1.bairro;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.stereotype.Repository;

import br.com.megasoftgyn.projetoversao1.pessoa.PessoaDto;

@Repository
public class BairroRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public List<Bairro> listarBairrosCriteria() {
        final CriteriaBuilder criteria = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Bairro> query = criteria.createQuery(Bairro.class);
        final Root<Bairro> clausula = query.from(Bairro.class);
        query.select(clausula);
        return this.entityManager.createQuery(query).getResultList();
    }
    
    public List<Bairro> listarBairrosPaginadoCriteria(final int numeroDaPagina) {
        final int tamanho = 8;
        final CriteriaBuilder criteria = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Bairro> query = criteria.createQuery(Bairro.class);
        final Root<Bairro> clausula = query.from(Bairro.class);
        final CriteriaQuery<Bairro> select = query.select(clausula);
        final TypedQuery<Bairro> typedQuery = this.entityManager.createQuery(select);
        typedQuery.setFirstResult((numeroDaPagina - 1) * tamanho);
        typedQuery.setMaxResults(tamanho);
        try {
            return typedQuery.getResultList();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void deleteBairroCriteria(final Long id) {
        final CriteriaBuilder criteria = this.entityManager.getCriteriaBuilder();
        final CriteriaDelete<Bairro> criteriaDelete = criteria.createCriteriaDelete(Bairro.class);
        final Root<Bairro> clausula = criteriaDelete.from(Bairro.class);
        criteriaDelete.where(criteria.equal(clausula, id));
        this.entityManager.createQuery(criteriaDelete).executeUpdate();
    }
    
    public void updateBairro(final Bairro bairroEditado, final Long id) {
        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        final CriteriaUpdate<Bairro> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Bairro.class);
        final Root<Bairro> root = criteriaUpdate.from(Bairro.class);
        criteriaUpdate.set(Bairro_.id, id);
        criteriaUpdate.where(criteriaBuilder.equal(root.get(Bairro_.id), id));
        this.entityManager.createQuery(criteriaUpdate).executeUpdate();
    }
    
    public void salvarBairro(final Bairro bairro) {
        this.entityManager.persist(bairro);
    }
    
    public void alterarBairro(final Bairro bairroEditado, final Long id) {
        // final Bairro bairro = this.entityManager.getReference(Bairro.class, id);
        this.entityManager.find(Bairro.class, id);
        this.entityManager.persist(bairroEditado);
    }
    
    public List<Bairro> buscaBairrosPaginado(final int numerodaPagina) {
        final int tamanho = 8;
        final String jpql = "select c from Bairro c";
        final TypedQuery<Bairro> typedQuery = this.entityManager.createQuery(jpql, Bairro.class);
        typedQuery.setFirstResult((numerodaPagina - 1) * tamanho);
        typedQuery.setMaxResults(tamanho);
        try {
            return typedQuery.getResultList();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Bairro> buscarTodosBairros() {
        final String jpql = "select c from Bairro c";
        final TypedQuery<Bairro> typedQuery = this.entityManager.createQuery(jpql, Bairro.class);
        return typedQuery.getResultList();
    }
    
    public List<PessoaDto> buscarBairro(final String nome) {
        final String jpql = "Select new br.com.megasoftgyn.projetoversao1.pessoa.PessoaDto(p.nome, p.cpf) FROM Pessoa p join fetch Bairro b ON b.nome= :nome AND b.id = p.endereco";
        final TypedQuery<PessoaDto> typedQuery = this.entityManager.createQuery(jpql, PessoaDto.class);
        typedQuery.setParameter("nome", nome);
        try {
            return typedQuery.getResultList();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public BigDecimal calcularIptuJuridica() {
        final String jpql = "SELECT SUM(b.valorDoIptu) FROM Bairro b, Endereco e, Pessoa p WHERE b.id=e.bairro and e.id=p.endereco and p.tipoPessoa=2";
        final TypedQuery<Double> typedQuery = this.entityManager.createQuery(jpql, Double.class);
        try {
            return new BigDecimal(typedQuery.getSingleResult().toString());
        } catch (final EntityNotFoundException notFound) {
            throw new RuntimeException("Falhou " + notFound);
        }
    }
    
    public void deleteBairro(final Long id) throws Exception {
        final Bairro bairro = this.entityManager.find(Bairro.class, id);
        try {
            this.entityManager.remove(bairro);
        } catch (final Exception e) {
            throw new Exception("Falha ao apagar" + e);
        }
    }
    
    public Bairro buscarBairroId(final Long id) {
        return this.entityManager.find(Bairro.class, id);
    }
    
    public List<String> nomesDosBairros() {
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);
        final Root<Bairro> bairro = criteriaQuery.from(Bairro.class);
        criteriaQuery.select(bairro.get(Bairro_.nome));
        final TypedQuery<String> typedQuery = this.entityManager.createQuery(criteriaQuery);
        try {
            return typedQuery.getResultList();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Selection<?>> camposBairro(final Root<Bairro> from) {
        final List<Selection<?>> campos = new ArrayList<>();
        campos.add(from.get(Bairro_.id));
        campos.add(from.get(Bairro_.nome));
        campos.add(from.get(Bairro_.valorDoIptu));
        return campos;
    }
}
