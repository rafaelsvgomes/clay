package br.com.clay.entidade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the produto database table.
 * 
 */
@Entity
@Table(name = "PRODUTO")
public class Produto extends ClayEntidade{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idproduto")
	private Long id;

	private Boolean bolVisivel;

	@Column(name = "dsproduto")
	private String descProduto;

	private String nomeProduto;

	private double percMargemVenda;

	private BigDecimal qtdAltura;

	private BigDecimal qtdLargura;

	private BigDecimal qtdPeso;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="idcategoria")
	private Categoria categoria;

	//bi-directional one-to-one association to Fornecedor
	@OneToOne
	@JoinColumn(name="idfornecedor")
	private Fornecedor fornecedor;

	//bi-directional many-to-one association to UnidadeVenda
	@ManyToOne
	@JoinColumn(name="idunidadevenda")
	private UnidadeVenda unidadeVenda;

	//bi-directional many-to-one association to ValorProduto
	@OneToMany(mappedBy="produto")
	private List<ProdutoValor> listaProdutoValor;

	public Produto() {
	}

	public void setIdProduto(Long idProduto) {
		this.id = idProduto;
	}

	public Boolean getBolVisivel() {
		return this.bolVisivel;
	}

	public void setBolVisivel(Boolean bolVisivel) {
		this.bolVisivel = bolVisivel;
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
		if(getListaProdutoValor() == null) {
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

    /* (non-Javadoc)
     * @see br.com.clay.entidade.ClayEntidade#getId()
     */
    @Override
    public Number getId() {
        return this.id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     * 
     * Sobrescrevendo para ser encontrado via converter
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     * 
     * Sobrescrevendo para ser encontrado via converter
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produto other = (Produto) obj;
        if (id == null) {
            if (other.getId() != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        return true;
    }
}