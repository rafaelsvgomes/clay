package br.com.clay.servico;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.clay.entidade.Cliente;
import br.com.clay.entidade.Endereco;
import br.com.clay.entidade.PessoaConta;
import br.com.clay.entidade.Telefone;

/**
 * Session Bean implementation class ClienteEJB
 */

@Stateless
@LocalBean
public class ClienteServicoEJB extends ClayPersistencia<Cliente, Long> {

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
        super(Cliente.class);
    }

    /**
     * @param id
     * @return Pessoa
     */
    @SuppressWarnings("unchecked")
    public Cliente obterPessoa(Long id) {
        Cliente cliente = em.find(Cliente.class, id);
        cliente.setListaEndereco(em.createNamedQuery(Endereco.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        cliente.setListaTelefone(em.createNamedQuery(Telefone.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        cliente.setListaPessoaConta(em.createNamedQuery(PessoaConta.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        return cliente;
    }
}
