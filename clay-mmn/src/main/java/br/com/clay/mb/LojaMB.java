package br.com.clay.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

import br.com.clay.entidade.Categoria;
import br.com.clay.entidade.Produto;
import br.com.clay.entidade.ProdutoImagem;
import br.com.clay.servico.LojaServicoEJB;
import br.com.clay.servico.ProdutoServicoEJB;


/**
 * @author Felipe.Rosa
 *
 */
@ManagedBean(name = "lojaMB")
@ViewScoped
public class LojaMB extends ClayMB{
	private static final long serialVersionUID = 2261640534496895534L;
	
    @EJB
    LojaServicoEJB ejb;
    
    @EJB
    ProdutoServicoEJB produtoEJB;
	
    private String filtroProduto;
    private MenuModel modelo;
	private List<Categoria> listaCategoria;
	private List<Produto> listaProduto;
	private Produto produtoSelecionado;
	private Long idCategoria;

	@PostConstruct
	public void init() {
		if (!isPostBack()) {
			this.pesquisaProdutos();
			this.preparaMenu();
		}
	}
	
	private void preparaMenu() {
		modelo = new DefaultMenuModel();
		DefaultSubMenu subMenuCategorias = new DefaultSubMenu();
		subMenuCategorias.setLabel("Categoria");
		
		DefaultMenuItem item;
		listaCategoria = ejb.findAll();
		for (Categoria categoria : listaCategoria) {
			item = new DefaultMenuItem();
			item.setAjax(false);
			item.setValue(categoria.getDescCategoria());
			item.setCommand("#{lojaMB.filtrarProdutosPorCategoria}");
			item.setParam("idCategoria", categoria.getId());
			subMenuCategorias.addElement(item);
		}
		
		modelo.addElement(subMenuCategorias);
	}
	
	private void pesquisaProdutos() {
		listaProduto = produtoEJB.listarProdutoDisponivelLoja();
	}
 		
	public MenuModel getModelo() {
		return modelo;
	}
	
	public List<Produto> getListaProduto() {
		return listaProduto;
	}
	
	public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }
 
	public String getFiltroProduto() {
		return filtroProduto;
	}
	
	public void setFiltroProduto(String filtroProduto) {
		this.filtroProduto = filtroProduto;
	}
	
	public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoEJB.obterProdutoComImagens(produtoSelecionado.getId());
    }
	
    public List<String> getListaImagensProdutoSelecionado() {
    	List<String> listaImagens = new ArrayList<String>();
    	for (ProdutoImagem produtoImagem : produtoSelecionado.getListaProdutoImagem()) {
    		listaImagens.add(produtoImagem.getDescCaminhoImagem());
    	}
    	return listaImagens;
    }
    
	public void filtrarProdutosPorCategoria(ActionEvent event) {
		MenuItem menuItem = ((MenuActionEvent) event).getMenuItem();
		idCategoria = Long.parseLong(menuItem.getParams().get("idCategoria").get(0));
		System.out.println("TESTE - " + idCategoria);
		listaProduto = produtoEJB.listarProdutoLoja(idCategoria, filtroProduto);
	}
	
	public void filtrarProdutosPorNome() {
		if (idCategoria == null) {
			listaProduto = produtoEJB.listarProdutoLoja(filtroProduto);
		} else {
			listaProduto = produtoEJB.listarProdutoLoja(idCategoria, filtroProduto);
		}
	}
	
	public void limparFiltro() {
		RequestContext.getCurrentInstance().reset(":formMenu");
		this.filtroProduto = null;
		this.idCategoria = null;
		this.pesquisaProdutos();
	}
	
}
