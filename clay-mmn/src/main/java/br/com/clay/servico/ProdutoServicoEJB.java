/**
 * Projeto:         Clay Cosm�ticos
 * Camada Projeto:  clay-mmn
 * Pacote:          br.com.clay.servico
 * Arquivo:         ProdutoServicoEJB.java
 * Data Cria��o:    8 de out de 2015
 */
package br.com.clay.servico;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.clay.entidade.Produto;
import br.com.clay.entidade.ValorProduto;

/**
 * ProdutoServicoEJB � respons�vel por 
 * 
 * @author Felipe
 */
@Stateless
@LocalBean
public class ProdutoServicoEJB extends ClayPersistencia<Produto, Long> {

    @PersistenceContext
    private EntityManager em;
    
    /* (non-Javadoc)
     * @see br.com.clay.servico.ClayPersistencia#getEntityManager()
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * @param produto
     */
    public ProdutoServicoEJB() {
        super(Produto.class);
    }

    /**
     * @param id
     * @return Produto
     */
    @SuppressWarnings("unchecked")
    public Produto obterProduto(Long id) {
    	Produto produto = em.find(Produto.class, id);
        produto.setValorProdutos(em.createNamedQuery(ValorProduto.LISTAR_POR_ID_PRODUTO).setParameter("idProduto", id).getResultList());
        return produto;
    }
    
}