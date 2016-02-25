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

import br.com.clay.entidade.Fornecedor;
import br.com.clay.entidade.PessoaConta;
import br.com.clay.entidade.PessoaEndereco;
import br.com.clay.entidade.PessoaTelefone;
import br.com.clay.entidade.UF;

/**
 * FonecedorServicoEJB responsavel por
 * 
 * @author Felipe
 */
@Stateless
@LocalBean
public class FornecedorServicoEJB extends ClayPersistencia<Fornecedor, Long> {

    @PersistenceContext
    private EntityManager em;

    /**
     * @param entityClass
     */
    public FornecedorServicoEJB() {
        super(Fornecedor.class);
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

    /**
     * @param id
     * @return Fornecedor
     */
    @SuppressWarnings("unchecked")
    public Fornecedor obterFornecedor(Long id) {
        Fornecedor fornecedor = em.find(Fornecedor.class, id);
        fornecedor.setListaEndereco(em.createNamedQuery(PessoaEndereco.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        fornecedor.setListaTelefone(em.createNamedQuery(PessoaTelefone.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        fornecedor.setListaPessoaConta(em.createNamedQuery(PessoaConta.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        return fornecedor;
    }
    
    /**
     * Método responsável por buscar a uf de acordo com seu ID
     * @param codUf
     * @return UF
     * 
     */
    public UF obterUF(String codUf){
        return em.find(UF.class, codUf);
    }
}
