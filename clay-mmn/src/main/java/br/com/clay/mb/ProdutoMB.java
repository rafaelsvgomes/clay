/**
 * Projeto:         Clay Cosméticos
 * Camada Projeto:  clay-mmn
 * Pacote:          br.com.clay.mb
 * Arquivo:         ProdutoMB.java
 * Data Criação:    8 de out de 2015
 */
package br.com.clay.mb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;

import br.com.clay.entidade.Categoria;
import br.com.clay.entidade.Fornecedor;
import br.com.clay.entidade.Produto;
import br.com.clay.entidade.ProdutoComposicao;
import br.com.clay.entidade.ProdutoValor;
import br.com.clay.entidade.UnidadeVenda;
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

    private Fornecedor fornecedor;
    private Categoria categoria;
    private UnidadeVenda unidadeVenda;
    private ProdutoValor produtoValor;
    private ProdutoComposicao produtoComposicaoPai;
    private ProdutoComposicao produtoComposicaoSelecionado;
    private List<ProdutoComposicao> listaProdutoComposicaoFilho;

    private List<Produto> listaProdutos;
    private List<Produto> listaProdutoDisponivelKit;
    private List<Produto> listaExcluirProdutosKit;
    private List<Produto> listaProdutosSelecionadosKit;
    private List<Fornecedor> listaFornecedore;
    private List<Categoria> listaCategoria;
    private List<UnidadeVenda> listaUnidadeVenda;

    private Boolean kit;

    public ProdutoMB() {
    }

    public void incluir() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            produto = new Produto();
            kit = Boolean.FALSE;
            setFornecedor();
            setCategoria();
            setUnidadeVenda();
            setProdutoValor();
        }
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    private void setFornecedor() {
        fornecedor = new Fornecedor();
        produto.setFornecedor(fornecedor);
        fornecedor.addProduto(produto);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    private void setCategoria() {
        categoria = new Categoria();
        produto.setCategoria(categoria);
        categoria.addProduto(produto);
    }

    public UnidadeVenda getUnidadeVenda() {
        return unidadeVenda;
    }

    private void setUnidadeVenda() {
        unidadeVenda = new UnidadeVenda();
        produto.setUnidadeVenda(unidadeVenda);
        unidadeVenda.addProduto(produto);
    }

    public ProdutoValor getProdutoValor() {
        return produtoValor;
    }

    private void setProdutoValor() {
        produtoValor = new ProdutoValor();
        produtoValor.setDataAtualizacao(Calendar.getInstance());
        produto.addProdutoValor(produtoValor);
        produtoValor.setProduto(produto);
    }

    public ProdutoComposicao getProdutoComposicaoPai() {
        return produtoComposicaoPai;
    }

    private void setProdutoComposicaoPai() {
        produto.setBolComposicao(kit);
        if (kit) {
            produtoComposicaoPai = new ProdutoComposicao();
            //TODO: felipe
            // produto.setProdutoComposicao(produtoComposicaoPai);
            setListaProdutoComposicaoFilho();
        } else {
            // produto.setProdutoComposicao(null);
            produto.setListaProdutoComposicaoFilho(null);
        }
    }

    public List<ProdutoComposicao> getListaProdutoComposicaoFilho() {
        return listaProdutoComposicaoFilho;
    }

    private void setListaProdutoComposicaoFilho() {
        listaProdutoComposicaoFilho = new ArrayList<ProdutoComposicao>();
        produto.setListaProdutoComposicaoFilho(listaProdutoComposicaoFilho);
    }

    public Long getIdSelecionado() {
        return idSelecionado;
    }

    public void setIdSelecionado(Long idSelecionado) {
        this.idSelecionado = idSelecionado;
    }

    public List<Produto> getListaProdutos() {
        if (!FacesContext.getCurrentInstance().isPostback() || listaProdutos == null) {
            listaProdutos = ejb.findAll();
        }
        return listaProdutos;
    }

    @SuppressWarnings("unchecked")
    public List<Fornecedor> getListaFornecedor() {
        if (listaFornecedore == null) {
            listaFornecedore = ejb.findAll(Fornecedor.class);
        }
        return listaFornecedore;
    }

    @SuppressWarnings("unchecked")
    public List<Categoria> getListaCategoria() {
        if (listaCategoria == null) {
            listaCategoria = ejb.findAll(Categoria.class);
        }
        return listaCategoria;
    }

    @SuppressWarnings("unchecked")
    public List<UnidadeVenda> getListaUnidadeVenda() {
        if (listaUnidadeVenda == null) {
            listaUnidadeVenda = ejb.findAll(UnidadeVenda.class);
        }
        return listaUnidadeVenda;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public Boolean getKit() {
        return kit;
    }

    public void setKit(Boolean kit) {
        this.kit = kit;
        setProdutoComposicaoPai();
    }

    public List<Produto> getListaProdutoDisponivelKit() {
        return listaProdutoDisponivelKit;
    }

    public void setListaProdutoDisponivelKit(List<Produto> listaProdutoDisponivelKit) {
        this.listaProdutoDisponivelKit = listaProdutoDisponivelKit;
    }

    public List<Produto> getListaExcluirProdutosKit() {
        return listaExcluirProdutosKit;
    }

    public void setListaExcluirProdutosKit(List<Produto> listaExcluirProdutosKit) {
        this.listaExcluirProdutosKit = listaExcluirProdutosKit;
    }

    public List<Produto> getListaProdutosSelecionadosKit() {
        return listaProdutosSelecionadosKit;
    }

    public void setListaProdutosSelecionadosKit(List<Produto> listaProdutosSelecionadosKit) {
        this.listaProdutosSelecionadosKit = listaProdutosSelecionadosKit;
    }

    public ProdutoComposicao getProdutoComposicaoSelecionado() {
        return produtoComposicaoSelecionado;
    }

    public void setProdutoComposicaoSelecionado(ProdutoComposicao produtoComposicaoSelecionado) {
        this.produtoComposicaoSelecionado = produtoComposicaoSelecionado;
    }

    public String tratarAbas(FlowEvent event) {
        if (event.getNewStep().equalsIgnoreCase("tabKit") && !this.kit) {
            if (event.getOldStep().equalsIgnoreCase("tabVenda")) {
                return "tabValor";
            } else {
                return "tabVenda";
            }
        } else {
            return event.getNewStep();
        }
    }

    public void editar() {
        if (idSelecionado == null) {
            return;
        }
        produto = ejb.obterProduto(idSelecionado);
        fornecedor = produto.getFornecedor();
        categoria = produto.getCategoria();
        unidadeVenda = produto.getUnidadeVenda();
        produtoValor = produto.getListaProdutoValor().get(0);
    }

    public String salvar() {
        try {
            produto.setBolVisivel(Boolean.TRUE);
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

}
