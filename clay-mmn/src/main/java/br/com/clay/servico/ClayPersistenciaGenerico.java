/**
 * Projeto:         Clay Cosméticos
 * Camada Projeto:  clay-mmn
 * Pacote:          br.com.clay.servico
 * Arquivo:         BasePersistenciaGenerico.java
 * Data Criação:    07/10/2015
 */
package br.com.clay.servico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.com.clay.entidade.ClayEntidade;

/**
 * BasePersistenciaGenerico
 * 
 * @author rafael.silva
 */
public class ClayPersistenciaGenerico<T extends ClayEntidade> {

    EntityManager em;

    public ClayPersistenciaGenerico(EntityManager entityManager) {
        em = entityManager;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<T> findAll(Class<T> entityClass) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

}
