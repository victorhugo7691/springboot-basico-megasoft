package br.com.megasoftgyn.projetoversao1.profissao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public class ProfissaoRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public List<ProfissaoDto> listarProfissorsDto() {
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<ProfissaoDto> query = builder.createQuery(ProfissaoDto.class);
        final Root<Profissao> profissao = query.from(Profissao.class);
        query.multiselect(profissao);
        final TypedQuery<ProfissaoDto> typedQuery = this.entityManager.createQuery(query);
        try {
            return typedQuery.getResultList();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void deleteProfissaoCriteria(final int id) {
        final CriteriaBuilder criteria = this.entityManager.getCriteriaBuilder();
        final CriteriaDelete<Profissao> criteriaDelete = criteria.createCriteriaDelete(Profissao.class);
        final Root<Profissao> clausula = criteriaDelete.from(Profissao.class);
        criteriaDelete.where(criteria.equal(clausula, id));
        this.entityManager.createQuery(criteriaDelete).executeUpdate();
    }
    
    public Profissao buscarProfissaoComId(final Long id) {
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Profissao> query = builder.createQuery(Profissao.class);
        final Root<Profissao> profissao = query.from(Profissao.class);
        query.where(builder.equal(profissao.get(Profissao_.id), id));
        query.select(profissao);
        final TypedQuery<Profissao> typedQuery = this.entityManager.createQuery(query);
        try {
            return typedQuery.getSingleResult();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Profissao> listarProfissoes() {
        final String jpql = "SELECT p FROM Profissao p";
        final Query query = this.entityManager.createQuery(jpql, Profissao.class);
        try {
            return query.getResultList();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Profissao salvarProfissao(final Profissao profissao) {
        this.entityManager.persist(profissao);
        return profissao;
    }
    
    public void deletarProfissao(final int id) {
        final Profissao profissao = this.entityManager.find(Profissao.class, id);
        this.entityManager.remove(id);
    }
}
