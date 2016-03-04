/**
 * Projeto:         Clay Cosmeticos
 * Camada Projeto:  clay-mmn
 * Pacote:          br.com.clay.servico
 * Arquivo:         FonecedorServicoEJB.java
 * Data Criacao:    10 de out de 2015
 */
package br.com.clay.servico;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.clay.entidade.Cliente;
import br.com.clay.entidade.ClienteSituacao;
import br.com.clay.entidade.LogPedidoSituacao;
import br.com.clay.entidade.Pedido;
import br.com.clay.entidade.StatusPagamento;
import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;
import br.com.uol.pagseguro.service.NotificationService;

/**
 * PedidoServicoEJB
 * 
 * @author George
 */
@Stateless
@LocalBean
public class PedidoServicoEJB extends ClayPersistencia<Pedido, Long> {

    @PersistenceContext
    private EntityManager em;

    /**
     * @param entityClass
     */
    public PedidoServicoEJB() {
        super(Pedido.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.clay.servico.ClayPersistencia#getEntityManager()
     */
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public EntityManager getEm() {
        return em;
    }

    /**
     * Método responsável por atualizar as transações do pagseguro.
     * 
     * @param codNotificao void
     * 
     */
    public void atualizarTransacaoPedido(String codNotificao) {
        Transaction transaction = null;
        try {
            transaction = NotificationService.checkTransaction(PagSeguroConfig.getAccountCredentials(), codNotificao);

            if (transaction != null) {
                System.out.println("############# NotificationService.checkTransaction ##############");
                System.out.println("code: " + transaction.getCode());
                System.out.println("reference: " + transaction.getReference());
                System.out.println("status: " + transaction.getStatus());
            }

            LogPedidoSituacao logPedido = new LogPedidoSituacao();
            logPedido.setCodTransacao(transaction.getCode());
            logPedido.setDataHoraPedidoSituacao(new Date());
            logPedido.setPedido(new Pedido(Long.parseLong(transaction.getReference())));
            logPedido.setStatusPagamento(new StatusPagamento(transaction.getStatus().getValue().shortValue()));

            if (logPedido.getStatusPagamento().getId() == StatusPagamento.PAGO) {
                Cliente cliente = obterClientePedido(logPedido.getPedido().getId());
                cliente.setClienteSituacao(new ClienteSituacao(ClienteSituacao.ATIVO));
                em.merge(cliente);
            }
            em.persist(logPedido);
        } catch (PagSeguroServiceException e) {
            System.err.println(e.getMessage());
        }
    }

    private Cliente obterClientePedido(Long idPedido) {
        return (Cliente) em.createNamedQuery(Pedido.OBTER_CLIENTE_PEDIDO).setParameter("idPedido", idPedido).getSingleResult();
    }

}
