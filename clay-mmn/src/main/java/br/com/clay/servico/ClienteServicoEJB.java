package br.com.clay.servico;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.clay.entidade.Cliente;
import br.com.clay.entidade.PessoaConta;
import br.com.clay.entidade.PessoaEndereco;
import br.com.clay.entidade.PessoaTelefone;
import br.com.clay.entidade.Produto;

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
        cliente.setListaEndereco(em.createNamedQuery(PessoaEndereco.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        cliente.setListaTelefone(em.createNamedQuery(PessoaTelefone.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        cliente.setListaPessoaConta(em.createNamedQuery(PessoaConta.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        return cliente;
    }

    @SuppressWarnings("unchecked")
    public Cliente obterCliente(String descUsuario) {
        Cliente cliente = (Cliente) em.createNamedQuery(Cliente.OBTER_POR_DESC_USUARIO).setParameter("descUsuario", descUsuario).getSingleResult();
        cliente.setListaTelefone(em.createNamedQuery(PessoaTelefone.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", cliente.getId()).getResultList());
        cliente.getPlanoAssinatura().getProduto().getListaProdutoFilho().get(0);
        cliente.getPlanoAssinatura().getProduto().setListaProdutoFilho(cliente.getPlanoAssinatura().getProduto().getListaProdutoFilho());
        return cliente;
    }

    /**
     * Método responsável por recuperar os produtos de um produto pai
     * 
     * @param idProdutoPai
     * @return List<Produto>
     * 
     */
    public List<Produto> obterProdutosKit(Long idProdutoPai) {
        Produto produtoPai = em.find(Produto.class, idProdutoPai);
        produtoPai.getListaProdutoFilho().get(0);
        return produtoPai.getListaProdutoFilho();
    }
}
