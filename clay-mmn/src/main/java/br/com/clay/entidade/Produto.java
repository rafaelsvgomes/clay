package br.com.clay.entidade;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the produto database table.
 * 
 */
@Entity
public class Produto extends ClayEntidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idproduto")
	private Long id;

	private Boolean bolVisivel;

	private String dsProduto;

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
	private List<ValorProduto> valorProdutos;

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

	public String getDsProduto() {
		return this.dsProduto;
	}

	public void setDsProduto(String dsProduto) {
		this.dsProduto = dsProduto;
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

	public List<ValorProduto> getValorProdutos() {
		return this.valorProdutos;
	}

	public void setValorProdutos(List<ValorProduto> valorProdutos) {
		this.valorProdutos = valorProdutos;
	}

	public ValorProduto addValorProduto(ValorProduto valorProduto) {
		if(getValorProdutos() == null) {
			setValorProdutos(new ArrayList<ValorProduto>());
		}
		getValorProdutos().add(valorProduto);
		valorProduto.setProduto(this);

		return valorProduto;
	}

	public ValorProduto removeValorProduto(ValorProduto valorProduto) {
		getValorProdutos().remove(valorProduto);
		valorProduto.setProduto(null);

		return valorProduto;
	}

    /* (non-Javadoc)
     * @see br.com.clay.entidade.ClayEntidade#getId()
     */
    @Override
    public Number getId() {
        return this.id;
    }

}