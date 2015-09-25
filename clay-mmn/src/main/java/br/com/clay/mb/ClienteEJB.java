package br.com.clay.mb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import br.com.clay.entidade.Pessoa;

/**
 * Session Bean implementation class ClienteEJB
 */
@Stateless
@LocalBean
public class ClienteEJB {

	@PersistenceContext(unitName = "clay_pu")
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ClienteEJB() {
    }

    
    public void salvar(Pessoa p){
    	p.setId(null);
    	em.persist(p);
    }
    
    public List<Pessoa> getClientes(){
    	CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Pessoa.class));
		return em.createQuery(cq).getResultList();
    }
}
