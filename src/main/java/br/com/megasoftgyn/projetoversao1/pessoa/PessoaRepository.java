package br.com.megasoftgyn.projetoversao1.pessoa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.stereotype.Repository;

import br.com.megasoftgyn.projetoversao1.bairro.Bairro;
import br.com.megasoftgyn.projetoversao1.bairro.Bairro_;
import br.com.megasoftgyn.projetoversao1.endereco.Endereco;
import br.com.megasoftgyn.projetoversao1.endereco.Endereco_;

@Repository
public class PessoaRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public List<PessoaDto> listarPessoaDeBairro(final String nome) {
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<PessoaDto> criteriaQuery = builder.createQuery(PessoaDto.class);
        final Root<Pessoa> pessoa = criteriaQuery.from(Pessoa.class);
        final Join<Pessoa, Endereco> endereco = pessoa.join(Pessoa_.endereco);
        final Join<Endereco, Bairro> bairro = endereco.join(Endereco_.bairro);
        criteriaQuery.multiselect(pessoa);
        criteriaQuery.where(builder.equal(bairro.get(Bairro_.nome), nome));
        criteriaQuery.where(builder.equal(bairro.get(Bairro_.id), endereco.get(Endereco_.bairro)));
        criteriaQuery.where(builder.equal(pessoa.get(Pessoa_.endereco), endereco.get(Endereco_.id)));
        final TypedQuery<PessoaDto> typedQuery = this.entityManager.createQuery(criteriaQuery);
        try {
            return typedQuery.getResultList();
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Pessoa> listarPessoasPaginado(final int numeroDaPagina) {
        final int tamanho = 8;
        final String jpql = "SELECT c FROM Pessoa c";
        final TypedQuery<Pessoa> typedQuery = this.entityManager.createQuery(jpql, Pessoa.class);
        typedQuery.setFirstResult((numeroDaPagina - 1) * tamanho);
        typedQuery.setMaxResults(tamanho);
        try {
            return typedQuery.getResultList();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<PessoaDto> listarPessoaDto() {
        final CriteriaBuilder criteria = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<PessoaDto> query = criteria.createQuery(PessoaDto.class);
        final Root<Pessoa> clausula = query.from(Pessoa.class);
        query.multiselect(this.camposPessoaDto(clausula));
        final TypedQuery<PessoaDto> typedQuery = this.entityManager.createQuery(query);
        try {
            return typedQuery.getResultList();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Optional<PessoaDto> consultarPessoaPeloId(final Long id) {
        final CriteriaBuilder criteria = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<PessoaDto> query = criteria.createQuery(PessoaDto.class);
        final Root<Pessoa> clausula = query.from(Pessoa.class);
        query.multiselect(this.camposPessoaDto(clausula));
        query.where(criteria.equal(clausula.get("id"), id));
        final TypedQuery<PessoaDto> typedQuery = this.entityManager.createQuery(query);
        try {
            return Optional.of(typedQuery.getSingleResult());
        } catch (final NoResultException e) {
            return Optional.empty();
        }
    }
    
    public void deletePessoaCriteria(final String cpf) {
        final CriteriaBuilder criteria = this.entityManager.getCriteriaBuilder();
        final CriteriaDelete<Pessoa> criteriaDelete = criteria.createCriteriaDelete(Pessoa.class);
        final Root<Pessoa> clausula = criteriaDelete.from(Pessoa.class);
        criteriaDelete.where(criteria.equal(clausula, cpf));
        try {
            this.entityManager.createQuery(criteriaDelete).executeUpdate();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public List<PessoaDto> buscarPessoaProfissao(final Integer id) {
        final String jpql = "SELECT new br.com.megasoftgyn.projetoversao1.pessoa.PessoaDto(nome, sobrenome, idade) FROM Pessoa p WHERE p.profissao = :id";
        final TypedQuery<PessoaDto> typedQuery = this.entityManager.createQuery(jpql, PessoaDto.class);
        typedQuery.setParameter("id", id);
        try {
            return typedQuery.getResultList();
        } catch (final NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Long buscarQuantidadePessoaTipo(final int tipo, final String bairro) {
        final String jpql = "SELECT COUNT(p) FROM Pessoa p, Endereco e, Bairro b WHERE p.tipoPessoa= '" + tipo + "' AND e.bairro=b.id AND b.nome= '" + bairro + "'";
        final TypedQuery<Long> typedQuery = this.entityManager.createQuery(jpql, Long.class);
        try {
            return typedQuery.getSingleResult();
        } catch (final EntityNotFoundException notFound) {
            throw new RuntimeException("Falhou " + notFound);
        } catch (final NonUniqueResultException nonUnique) {
            throw new RuntimeException("Não é único " + nonUnique);
        }
    }
    
    public Pessoa salvarPessoa(final Pessoa pessoa) {
        this.entityManager.persist(pessoa);
        return pessoa;
    }
    
    public List<Pessoa> buscarPessoas() {
        final Query query = this.entityManager.createQuery("SELECT c FROM Pessoa c", Pessoa.class);
        return query.getResultList();
    }
    
    public List<Pessoa> buscarNomePessoas() {
        final Query query = this.entityManager.createQuery("SELECT new br.com.megasoftgyn.projetoversao1.pessoa.Pessoa(nome) FROM Pessoa c", Pessoa.class);
        return query.getResultList();
    }
    
    public void deletarPessoa(final String cpf) {
        final Pessoa pessoa = this.entityManager.find(Pessoa.class, cpf);
        this.entityManager.remove(pessoa);
    }
    
    private List<Selection<?>> camposPessoaDto(final Root<Pessoa> from) {
        final List<Selection<?>> campos = new ArrayList<>();
        campos.add(from.get(Pessoa_.nome));
        campos.add(from.get(Pessoa_.sobrenome));
        campos.add(from.get(Pessoa_.cpf));
        campos.add(from.get(Pessoa_.idade));
        return campos;
    }
}
