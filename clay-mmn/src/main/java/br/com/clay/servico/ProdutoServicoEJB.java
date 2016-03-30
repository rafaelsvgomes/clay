/**
 * Projeto:         Clay Cosmeticos
 * Camada Projeto:  clay-mmn
 * Pacote:          br.com.clay.servico
 * Arquivo:         ProdutoServicoEJB.java
 * Data Criacao:    8 de out de 2015
 */
package br.com.clay.servico;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.clay.entidade.Produto;
import br.com.clay.entidade.ProdutoComposicao;
import br.com.clay.entidade.ProdutoImagem;
import br.com.clay.entidade.ProdutoValor;

/**
 * ProdutoServicoEJB e responsavel por
 * 
 * @author Felipe
 */
@Stateless
@LocalBean
public class ProdutoServicoEJB extends ClayPersistencia<Produto, Long> {

    @PersistenceContext
    private EntityManager em;

    /*
     * (non-Javadoc)
     * 
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
        produto.setListaProdutoValor(em.createNamedQuery(ProdutoValor.LISTAR_POR_ID_PRODUTO).setParameter("idProduto", id).getResultList());
        return produto;
    }

    @SuppressWarnings("unchecked")
    public List<ProdutoComposicao> obterProdutoInclusoKit(Long idProduto) {
        return em.createNamedQuery(ProdutoComposicao.LISTAR_PRODUTOS_INCLUSOS).setParameter("idProduto", idProduto).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Produto> listarProdutoDisponivelKit() {
        return em.createNamedQuery(Produto.LISTAR_PRODUTO_DISPONIVEL_KIT).getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Produto> listarProdutoDisponivelLoja() {
    	return em.createNamedQuery(Produto.LISTAR_PRODUTO_LOJA).getResultList();
    }
    
    @SuppressWarnings("unchecked")
   	public List<Produto> listarProdutoLoja(Long idCategoria) {
   		return em.createNamedQuery(Produto.LISTAR_PRODUTO_CATEGORIA_LOJA).setParameter("idCategoria", idCategoria).getResultList();
    }
    
    @SuppressWarnings("unchecked")
   	public List<Produto> listarProdutoLoja(String nomeProduto) {
   		return em.createNamedQuery(Produto.LISTAR_PRODUTO_NOME_LOJA).setParameter("nomeProduto", "%" + nomeProduto.toLowerCase() + "%").getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Produto> listarProdutoLoja(Long idCategoria, String nomeProduto) {
		return em.createNamedQuery(Produto.LISTAR_PRODUTO_NOME_CATEGORIA_LOJA).setParameter("idCategoria", idCategoria).setParameter("nomeProduto", "%" + nomeProduto.toLowerCase() + "%").getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public Produto obterProdutoComImagens(Long id) {
    	Produto produto = this.obterProduto(id);
    	produto.setListaProdutoImagem(em.createNamedQuery(ProdutoImagem.LISTAR_POR_ID_PRODUTO).setParameter("idProduto", id).getResultList());
    	return produto; 
    }
}
