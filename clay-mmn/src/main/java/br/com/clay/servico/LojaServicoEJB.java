package br.com.clay.servico;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.clay.entidade.Categoria;

/**
 * @author Felipe.Rosa
 *
 */
@Stateless
@LocalBean
public class LojaServicoEJB extends ClayPersistencia<Categoria, Long>{

	@PersistenceContext
    private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public LojaServicoEJB() {
		super(Categoria.class);
	}

	public List<Categoria> listarCategorias() {
		return this.findAll();
	}

}
