package br.com.clay.servico;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.clay.entidade.Cliente;
import br.com.clay.entidade.LogPedidoSituacao;
import br.com.clay.entidade.Pedido;
import br.com.clay.entidade.PedidoTipo;
import br.com.clay.entidade.PessoaConta;
import br.com.clay.entidade.PessoaEndereco;
import br.com.clay.entidade.PessoaTelefone;
import br.com.clay.entidade.PlanoAssinatura;
import br.com.clay.entidade.Produto;
import br.com.clay.entidade.ProdutoValor;

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

    @SuppressWarnings("unchecked")
    public List<Cliente> listarClientesSimples() {
        return em.createNamedQuery(Cliente.LISTAR_CLIENTES_SIMPLES).getResultList();
    }

    /**
     * @param id
     * @return Pessoa
     */
    @SuppressWarnings("unchecked")
    public Cliente obterPessoa(Long id) {
        Cliente cliente = (Cliente) em.createNamedQuery(Cliente.OBTER_CLIENTE_EDITAR).setParameter("idCliente", id).getSingleResult();

        cliente.setListaEndereco(em.createNamedQuery(PessoaEndereco.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        cliente.setListaTelefone(em.createNamedQuery(PessoaTelefone.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        cliente.setListaPessoaConta(em.createNamedQuery(PessoaConta.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        return cliente;
    }

    @SuppressWarnings("unchecked")
    public Cliente obterCliente(Long idCliente) {
        Cliente cliente = find(idCliente);
        if (cliente != null) {
            cliente.setListaTelefone(em.createNamedQuery(PessoaTelefone.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", cliente.getId()).getResultList());
            cliente.getPlanoAssinatura().getProduto().getListaProdutoFilho().get(0);
            cliente.getPlanoAssinatura().getProduto().setListaProdutoFilho(cliente.getPlanoAssinatura().getProduto().getListaProdutoFilho());
        }
        return cliente;
    }

    public Boolean emailJaUtilizado(String descUsuario) {
        try {
            em.createNamedQuery(Cliente.OBTER_POR_DESC_USUARIO).setParameter("descUsuario", descUsuario).getSingleResult();
            return Boolean.TRUE;
        } catch (NoResultException e) {
            return Boolean.FALSE;
        }
    }

    public Boolean cpfCnpjJaUtilizado(String numCpfCnpj, Long idClienteSelecionado) {
        try {
            if (idClienteSelecionado != null) {
                em.createNamedQuery(Cliente.OBTER_POR_NUM_CPF_CNPJ_IGNORA_SELECIONADO).setParameter("numCpfCnpj", numCpfCnpj).setParameter("idSelecionado", idClienteSelecionado)
                        .getSingleResult();
            } else {
                em.createNamedQuery(Cliente.OBTER_POR_NUM_CPF_CNPJ).setParameter("numCpfCnpj", numCpfCnpj).getSingleResult();
            }
            return Boolean.TRUE;
        } catch (NoResultException e) {
            return Boolean.FALSE;
        }
    }

    /**
     * Método responsável por recuperar os produtos de um produto pai
     * 
     * @param idProdutoPai
     * @return List<Produto>
     * 
     */
    public List<Produto> listarProdutosKit(Long idProdutoPai) {
        Produto produtoPai = em.find(Produto.class, idProdutoPai);
        produtoPai.getListaProdutoFilho().get(0);
        return produtoPai.getListaProdutoFilho();
    }

    /**
     * @return Object
     * 
     */
    @SuppressWarnings("unchecked")
    public List<Cliente> listarClientesIndicadores() {
        return em.createNamedQuery(Cliente.LISTAR_CLIENTES_INDICADORES).getResultList();
    }

    /**
     * @return List<PlanoAssinatura>
     * 
     */
    @SuppressWarnings("unchecked")
    public List<PlanoAssinatura> listarPlanoAssinatura() {
        return em.createNamedQuery(PlanoAssinatura.LISTAR_SIPLES).getResultList();
    }

    /**
     * Método responsável por
     * 
     * @param id
     * @return ProdutoValor
     * 
     */
    public ProdutoValor obterValorProduto(Long idProduto) {
        return (ProdutoValor) em.createNamedQuery(ProdutoValor.OBTER_PRODUTO_VALOR_ATUAL).setParameter("idProduto", idProduto).setMaxResults(1).getSingleResult();
    }

    public void salvarPedido(Pedido pedido) {
        em.persist(pedido);
    }

    public void removerPedido(Pedido pedido) {
        Pedido p = em.find(Pedido.class, pedido.getId());
        p.getListaPedidoProduto().get(0);
        em.remove(p);
    }

    public void salvarLogPedidoSituacao(LogPedidoSituacao logPedidoSituacao) {
        em.persist(logPedidoSituacao);
    }

    public LogPedidoSituacao obterLogPedidoSituacao(Long idCliente) {
        LogPedidoSituacao log = null;
        try {
            log = (LogPedidoSituacao) em.createNamedQuery(LogPedidoSituacao.OBTER_ULTIMO_LOG_PEDIDO_SITUACAO_CLIENTE).setParameter("idCliente", idCliente)
                    .setParameter("idPedidoTipoAssinatura", PedidoTipo.ASSINATURA).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return log;
    }
}
