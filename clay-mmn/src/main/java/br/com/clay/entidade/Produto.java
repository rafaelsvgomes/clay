package br.com.clay.entidade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for the produto database table.
 * 
 */
@Entity
@Table(name = "PRODUTO")
@SequenceGenerator(name = "seqproduto", sequenceName = "seqproduto", allocationSize = 1)
@NamedQueries({ 
	@NamedQuery(name = Produto.LISTAR_PRODUTO_DISPONIVEL_KIT, query = "SELECT p FROM Produto p WHERE p.bolComposicao = false"),
	@NamedQuery(name = Produto.LISTAR_PRODUTO_LOJA, query = "SELECT new br.com.clay.entidade.Produto(p.id, p.nomeProduto, p.categoria.id, pi.descCaminhoImagem)  FROM ProdutoImagem pi INNER JOIN pi.produto p WHERE p.bolVisivel = true AND pi.bolPadrao = true"),
	@NamedQuery(name = Produto.LISTAR_PRODUTO_NOME_LOJA, query = "SELECT new br.com.clay.entidade.Produto(p.id, p.nomeProduto, p.categoria.id, pi.descCaminhoImagem)  FROM ProdutoImagem pi INNER JOIN pi.produto p WHERE p.bolVisivel = true AND pi.bolPadrao = true AND LOWER(p.nomeProduto) LIKE :nomeProduto"),
	@NamedQuery(name = Produto.LISTAR_PRODUTO_CATEGORIA_LOJA, query = "SELECT new br.com.clay.entidade.Produto(p.id, p.nomeProduto, p.categoria.id, pi.descCaminhoImagem)  FROM ProdutoImagem pi INNER JOIN pi.produto p WHERE p.bolVisivel = true AND pi.bolPadrao = true AND p.categoria.id = :idCategoria"),
	@NamedQuery(name = Produto.LISTAR_PRODUTO_NOME_CATEGORIA_LOJA, query = "SELECT new br.com.clay.entidade.Produto(p.id, p.nomeProduto, p.categoria.id, pi.descCaminhoImagem)  FROM ProdutoImagem pi INNER JOIN pi.produto p WHERE p.bolVisivel = true AND pi.bolPadrao = true AND p.categoria.id = :idCategoria AND LOWER(p.nomeProduto) LIKE :nomeProduto")})
public class Produto extends ClayEntidade {
    private static final long serialVersionUID = 1L;

    public static final String LISTAR_PRODUTO_DISPONIVEL_KIT = "Produto.listarProdutoDisponivelKit";
    public static final String LISTAR_PRODUTO_LOJA = "Produto.listarProdutoLoja";
    public static final String LISTAR_PRODUTO_NOME_LOJA = "Produto.listarProdutoNomeLoja";
    public static final String LISTAR_PRODUTO_CATEGORIA_LOJA = "Produto.listarProdutoCategoriaLoja";
    public static final String LISTAR_PRODUTO_NOME_CATEGORIA_LOJA = "Produto.listarProdutoNomeCategoriaLoja";
    

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqproduto")
    @Column(name = "idproduto", unique = true, nullable = false)
    private Long id;

    private boolean bolVisivel;

    private boolean bolComposicao;

    @Column(name = "dsproduto")
    private String descProduto;

    @NotEmpty(message = "{produto.nome.vazio}")
    private String nomeProduto;

    private double percMargemVenda;

    private BigDecimal qtdAltura;

    private BigDecimal qtdLargura;

    private BigDecimal qtdPeso;

    @Column(name = "vlpontoproduto")
    private BigDecimal valorPontoProduto;

    @ManyToOne
    @JoinColumn(name = "idcategoria")
    private Categoria categoria;

    @OneToOne
    @JoinColumn(name = "idfornecedor")
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "idunidadevenda")
    private UnidadeVenda unidadeVenda;

    @OneToMany(mappedBy = "produto")
    private List<ProdutoValor> listaProdutoValor;

    @OneToMany(mappedBy = "produtoPai")
    private List<ProdutoComposicao> listaProdutoComposicaoPai;

    @OneToMany(mappedBy = "produtoFilho")
    private List<ProdutoComposicao> listaProdutoComposicaoFilho;

    @ManyToMany
    @JoinTable(name = "produtoComposicao", joinColumns = { @JoinColumn(name = "idProduto") }, inverseJoinColumns = { @JoinColumn(name = "idProdutoItemComp") })
    private List<Produto> listaProdutoFilho;
    
    @OneToMany(mappedBy = "produto")
    private List<ProdutoImagem> listaProdutoImagem;

    @Transient
    private ProdutoImagem produtoImagemPadrao;

    public Produto() {
    }
    
    /**
     * Construtor da QUERY - LISTAR_PRODUTO_LOJA
     * 
     * @param id
     * @param nomeProduto
     * @param idCategoria
     * @param descCaminhoImagem
     */
    public Produto(Long id, String nomeProduto, Long idCategoria, String descCaminhoImagem) {
		super();
		this.id = id;
		this.nomeProduto = nomeProduto;
		this.categoria = new Categoria(idCategoria);
		this.produtoImagemPadrao = new ProdutoImagem(descCaminhoImagem);
	}

	public void setId(Long id) {
        this.id = id;
    }

    public boolean getBolVisivel() {
        return this.bolVisivel;
    }

    public void setBolVisivel(boolean bolVisivel) {
        this.bolVisivel = bolVisivel;
    }

    public boolean isBolComposicao() {
        return bolComposicao;
    }

    public void setBolComposicao(boolean bolComposicao) {
        this.bolComposicao = bolComposicao;
    }

    public String getDescProduto() {
        return this.descProduto;
    }

    public void setDescProduto(String descProduto) {
        this.descProduto = descProduto;
    }

    public String getNomeProduto() {
        return this.nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getPercMargemVenda() {
        return this.percMargemVenda;
    }

    public void setPercMargemVenda(double percMargemVenda) {
        this.percMargemVenda = percMargemVenda;
    }

    public BigDecimal getQtdAltura() {
        return this.qtdAltura;
    }

    public void setQtdAltura(BigDecimal qtdAltura) {
        this.qtdAltura = qtdAltura;
    }

    public BigDecimal getQtdLargura() {
        return this.qtdLargura;
    }

    public void setQtdLargura(BigDecimal qtdLargura) {
        this.qtdLargura = qtdLargura;
    }

    public BigDecimal getQtdPeso() {
        return this.qtdPeso;
    }

    public void setQtdPeso(BigDecimal qtdPeso) {
        this.qtdPeso = qtdPeso;
    }

    public BigDecimal getValorPontoProduto() {
        return valorPontoProduto;
    }

    public void setValorPontoProduto(BigDecimal valorPontoProduto) {
        this.valorPontoProduto = valorPontoProduto;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Fornecedor getFornecedor() {
        return this.fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public UnidadeVenda getUnidadeVenda() {
        return this.unidadeVenda;
    }

    public void setUnidadeVenda(UnidadeVenda unidadeVenda) {
        this.unidadeVenda = unidadeVenda;
    }

    public List<ProdutoValor> getListaProdutoValor() {
        return this.listaProdutoValor;
    }

    public void setListaProdutoValor(List<ProdutoValor> listaProdutoValor) {
        this.listaProdutoValor = listaProdutoValor;
    }

    public ProdutoValor addProdutoValor(ProdutoValor produtoValor) {
        if (getListaProdutoValor() == null) {
            setListaProdutoValor(new ArrayList<ProdutoValor>());
        }
        getListaProdutoValor().add(produtoValor);
        produtoValor.setProduto(this);

        return produtoValor;
    }

    public ProdutoValor removeProdutoValor(ProdutoValor produtoValor) {
        getListaProdutoValor().remove(produtoValor);
        produtoValor.setProduto(null);

        return produtoValor;
    }

    public List<ProdutoComposicao> getListaProdutoComposicaoFilho() {
        return listaProdutoComposicaoFilho;
    }

    public void setListaProdutoComposicaoFilho(List<ProdutoComposicao> listaProdutoComposicaoFilho) {
        this.listaProdutoComposicaoFilho = listaProdutoComposicaoFilho;
    }

    public ProdutoComposicao addProdutoComposicaoFilho(ProdutoComposicao produtoComposicaoFilho) {
        if (getListaProdutoComposicaoFilho() == null) {
            setListaProdutoComposicaoFilho(new ArrayList<ProdutoComposicao>());
        }
        getListaProdutoComposicaoFilho().add(produtoComposicaoFilho);
        produtoComposicaoFilho.setProdutoPai(this);

        return produtoComposicaoFilho;
    }

    public ProdutoComposicao removeProdutoComposicaoFilho(ProdutoComposicao produtoComposicaoFilho) {
        getListaProdutoComposicaoFilho().remove(produtoComposicaoFilho);
        produtoComposicaoFilho.setProdutoPai(null);

        return produtoComposicaoFilho;
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.clay.entidade.ClayEntidade#getId()
     */
    @Override
    public Long getId() {
        return this.id;
    }

    public List<ProdutoComposicao> getListaProdutoComposicaoPai() {
        return listaProdutoComposicaoPai;
    }

    public void setListaProdutoComposicaoPai(List<ProdutoComposicao> listaProdutoComposicaoPai) {
        this.listaProdutoComposicaoPai = listaProdutoComposicaoPai;
    }

    public List<Produto> getListaProdutoFilho() {
        return listaProdutoFilho;
    }

    public void setListaProdutoFilho(List<Produto> listaProdutoFilho) {
        this.listaProdutoFilho = listaProdutoFilho;
    }

	public List<ProdutoImagem> getListaProdutoImagem() {
		return listaProdutoImagem;
	}

	public void setListaProdutoImagem(List<ProdutoImagem> listaProdutoImagem) {
		this.listaProdutoImagem = listaProdutoImagem;
	}
    
    /**
     * Retorna o �ltimo ProdutoValor cadastrado.
     * 
     * @return
     */
    public ProdutoValor getProdutoValorValido() {
    	if (getListaProdutoValor() != null && !getListaProdutoValor().isEmpty()) {
    		return getListaProdutoValor().get(getListaProdutoValor().size() - 1);
    	}
    	return null;
    }

	public ProdutoImagem getProdutoImagemPadrao() {
		return produtoImagemPadrao;
	}

	public void setProdutoImagemPadrao(ProdutoImagem produtoImagemPadrao) {
		this.produtoImagemPadrao = produtoImagemPadrao;
	}
    
    
}