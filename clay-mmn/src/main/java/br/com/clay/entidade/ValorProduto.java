package br.com.clay.entidade;

import java.io.Serializable;
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

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtAtualizacao;

	private double vlCusto;

	private double vlDesconto;

	private double vlProduto;

	//bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name="idproduto")
	private Produto produto;

	public ValorProduto() {
	}
	
	public void setIdValorProduto(Long idValorProduto) {
		this.id = idValorProduto;
	}

	public Calendar getDtAtualizacao() {
		return this.dtAtualizacao;
	}

	public void setDtAtualizacao(Calendar dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	public double getVlCusto() {
		return this.vlCusto;
	}

	public void setVlCusto(double vlCusto) {
		this.vlCusto = vlCusto;
	}

	public double getVlDesconto() {
		return this.vlDesconto;
	}

	public void setVlDesconto(double vlDesconto) {
		this.vlDesconto = vlDesconto;
	}

	public double getVlProduto() {
		return this.vlProduto;
	}

	public void setVlProduto(double vlProduto) {
		this.vlProduto = vlProduto;
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