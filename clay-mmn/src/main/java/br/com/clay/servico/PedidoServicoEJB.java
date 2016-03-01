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

import br.com.clay.entidade.Cliente;
import br.com.clay.entidade.OrigemPagamento;
import br.com.clay.entidade.Pedido;
import br.com.clay.entidade.PedidoSituacao;
import br.com.clay.entidade.PedidoTipo;

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

}
