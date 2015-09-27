package br.com.clay.servico;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.clay.entidade.Pessoa;

/**
 * Session Bean implementation class ClienteEJB
 */

@Stateless
@LocalBean
public class ClienteServicoEJB extends BasePersistencia<Pessoa, Long> {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
    /**
     * Default constructor. 
     */
    public ClienteServicoEJB() {
    	super(Pessoa.class);
    }
}
