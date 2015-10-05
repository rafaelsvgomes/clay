package br.com.clay.servico;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.clay.entidade.Endereco;
import br.com.clay.entidade.Pessoa;
import br.com.clay.entidade.UF;

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

    @SuppressWarnings("unchecked")
    public List<UF> listarUfs() {
        Query query = em.createNamedQuery(UF.LISTAR);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public Pessoa obterPessoa(Long id) {
        Pessoa pessoa = em.find(Pessoa.class, id);
        pessoa.setListaEndereco(em.createNamedQuery(Endereco.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        return pessoa;
    }
}
