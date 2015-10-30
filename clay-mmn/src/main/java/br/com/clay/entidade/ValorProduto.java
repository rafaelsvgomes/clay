package br.com.clay.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the valorproduto database table.
 * 
 */
@Entity
@NamedQuery(name = ValorProduto.LISTAR_POR_ID_PRODUTO, query = "SELECT vp FROM ValorProduto vp WHERE vp.produto.id = :idProduto")
public class ValorProduto extends ClayEntidade implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String LISTAR_POR_ID_PRODUTO = "listarValorProdutoPorIdProduto";
	
	@Id
	@Column(name = "idvalorproduto")
	private Long id;

	@Column(name = "dtatualizacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataAtualizacao;

	@Column(name = "vlcusto")
	private BigDecimal valorCusto;

	@Column(name = "vldesconto")
	private BigDecimal valorDesconto;

	@Column(name = "vlproduto")
	private BigDecimal valorProduto;

	//bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name="idproduto")
	private Produto produto;

	public ValorProduto() {
	}
	
	public void setIdValorProduto(Long idValorProduto) {
		this.id = idValorProduto;
	}

	public Calendar getDataAtualizacao() {
		return this.dataAtualizacao;
	}

	public void setDataAtualizacao(Calendar dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public BigDecimal getValorCusto() {
		return this.valorCusto;
	}

	public void setValorCusto(BigDecimal valorCusto) {
		this.valorCusto = valorCusto;
	}

	public BigDecimal getValorDesconto() {
		return this.valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorProduto() {
		return this.valorProduto;
	}

	public void setValorProduto(BigDecimal valorProduto) {
		this.valorProduto = valorProduto;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

    /* (non-Javadoc)
     * @see br.com.clay.entidade.ClayEntidade#getId()
     */
    @Override
    public Number getId() {
        return this.id;
    }

}