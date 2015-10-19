package br.com.clay.servico;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.clay.entidade.Endereco;
import br.com.clay.entidade.Pessoa;
import br.com.clay.entidade.PessoaConta;
import br.com.clay.entidade.Telefone;

/**
 * Session Bean implementation class ClienteEJB
 */

@Stateless
@LocalBean
public class ClienteServicoEJB extends ClayPersistencia<Pessoa, Long> {

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

    /**
     * @param id
     * @return Pessoa
     */
    @SuppressWarnings("unchecked")
    public Pessoa obterPessoa(Long id) {
        Pessoa pessoa = em.find(Pessoa.class, id);
        pessoa.setListaEndereco(em.createNamedQuery(Endereco.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        pessoa.setListaTelefone(em.createNamedQuery(Telefone.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        pessoa.setListaPessoaConta(em.createNamedQuery(PessoaConta.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        return pessoa;
    }
}
