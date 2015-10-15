/**
 * Projeto:         Clay Cosméticos
 * Camada Projeto:  clay-mmn
 * Pacote:          br.com.clay.mb
 * Arquivo:         ProdutoMB.java
 * Data Criação:    8 de out de 2015
 */
package br.com.clay.mb;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.clay.entidade.Categoria;
import br.com.clay.entidade.Fornecedor;
import br.com.clay.entidade.Produto;
import br.com.clay.entidade.UnidadeVenda;
import br.com.clay.entidade.ValorProduto;
import br.com.clay.servico.ProdutoServicoEJB;
import br.com.clay.util.MensagemUtil;

/**
 * ProdutoMB é responsável por 
 * 
 * @author Felipe
 */
@ManagedBean(name = "produtoMB")
@SessionScoped
public class ProdutoMB extends ClayMB {
    private static final long serialVersionUID = -1164826778001209860L;

	private static final String LISTA_PRODUTO = "lista_produto";

    @EJB
    ProdutoServicoEJB ejb;

    private Long idSelecionado;    
    private Produto produto;
    private List<Produto> produtos;
    private List<Fornecedor> listaFornecedores;
    private List<Categoria> listaCategorias;
    private List<UnidadeVenda> listaUnidadeVendas;
    
    private Fornecedor fornecedor;
    private Categoria categoria;
	private UnidadeVenda unidadeVenda;
	private ValorProduto valorProduto;
    
    public ProdutoMB() {
    }

    public void incluir() {
    	if (!FacesContext.getCurrentInstance().isPostback()) {
    		produto = new Produto();
    		setFornecedor();
    		setCategoria();
    		setUnidadeVenda();
    		setValorProduto();
    	}
    }
    
    private void setFornecedor() {
    	fornecedor = new Fornecedor();
    	produto.setFornecedor(fornecedor);
    	fornecedor.addProduto(produto);
    }
    
    private void setCategoria() {
    	categoria = new Categoria();
    	produto.setCategoria(categoria);
    	categoria.addProduto(produto);
    }
    
    private void setUnidadeVenda() {
    	unidadeVenda = new UnidadeVenda();
    	produto.setUnidadeVenda(unidadeVenda);
    	unidadeVenda.addProduto(produto);
    }
    
    private void setValorProduto() {
    	valorProduto = new ValorProduto();
    	valorProduto.setDtAtualizacao(Calendar.getInstance());
    	produto.addValorProduto(valorProduto);
    	valorProduto.setProduto(produto);
    }
    
    public void editar() {
    	if (idSelecionado == null) {
            return;
        }
    	produto = ejb.obterProduto(idSelecionado);
        fornecedor = produto.getFornecedor();
        categoria = produto.getCategoria();
    	unidadeVenda = produto.getUnidadeVenda();
    	valorProduto = produto.getValorProdutos().get(0);
    }
    
    public String salvar() {
    	try {
            ejb.save(produto);
        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemUtil.addMensagem("msg.erro.salvar.produto", ex.getMessage());
            return "";
        }
    	
        return LISTA_PRODUTO;
    }
    
    public String remover() {
    	try {
            ejb.remove(produto);
            System.out.println("produto removido");
        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemUtil.addMensagem("msg.erro.remover.produto", ex.getMessage());
            return "";
        }
        return LISTA_PRODUTO;
    }
    
    public Produto getProduto() {
    	return produto;
    }
    
    public List<Produto> getProdutos() {
        if (!FacesContext.getCurrentInstance().isPostback() || produtos == null) {
            produtos = ejb.findAll();
        }
        return produtos;
    }  
    
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public UnidadeVenda getUnidadeVenda() {
		return unidadeVenda;
	}

	public ValorProduto getValorProduto() {
		return valorProduto;
	}

	public Long getIdSelecionado() {
		return idSelecionado;
	}

	public void setIdSelecionado(Long idSelecionado) {
		this.idSelecionado = idSelecionado;
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> getListaFornecedores() {
		if(listaFornecedores == null) {
			listaFornecedores = ejb.findAll(Fornecedor.class);
		}
		return listaFornecedores;
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> getListaCategorias() {
		if(listaCategorias == null) {
			listaCategorias = ejb.findAll(Categoria.class);
		}
		return listaCategorias;
	}

	@SuppressWarnings("unchecked")
	public List<UnidadeVenda> getListaUnidadeVendas() {
		if(listaUnidadeVendas == null) {
			listaUnidadeVendas = ejb.findAll(UnidadeVenda.class);
		}
		return listaUnidadeVendas;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
    
    
}
