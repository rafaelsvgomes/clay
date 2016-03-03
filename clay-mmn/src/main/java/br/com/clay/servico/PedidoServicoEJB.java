/**
 * Projeto:         Clay Cosmeticos
 * Camada Projeto:  clay-mmn
 * Pacote:          br.com.clay.servico
 * Arquivo:         FonecedorServicoEJB.java
 * Data Criacao:    10 de out de 2015
 */
package br.com.clay.servico;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.clay.entidade.Pedido;
import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;
import br.com.uol.pagseguro.service.NotificationService;

/**
 * PedidoServicoEJB responsavel por
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
     * Método responsável por
     * 
     * @param codNotificao void
     * 
     */
    public void atualizarTransacaoPedido(String codNotificao) {
        Transaction transaction = null;
        try {
            transaction = NotificationService.checkTransaction(PagSeguroConfig.getAccountCredentials(), codNotificao);
        } catch (PagSeguroServiceException e) {
            System.err.println(e.getMessage());
        }

        if (transaction != null) {
            System.out.println("#############NotificationService.checkTransaction##############");
            System.out.println("code: " + transaction.getCode());
            System.out.println("reference: " + transaction.getReference());
            System.out.println("status: " + transaction.getStatus());
        }

    }

}
