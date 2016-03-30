package br.com.clay.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Felipe.Rosa
 *
 */
@Entity
@Table(name = "PRODUTOIMAGEM")
@SequenceGenerator(name = "seqprodutoimagem", sequenceName = "seqprodutoimagem", allocationSize = 1)
@NamedQueries({ @NamedQuery(name = ProdutoImagem.LISTAR_POR_ID_PRODUTO, query = "SELECT pi FROM ProdutoImagem pi WHERE pi.produto.id = :idProduto")})
public class ProdutoImagem extends ClayEntidade {
	private static final long serialVersionUID = -5917715876701900034L;
	
	public static final String LISTAR_POR_ID_PRODUTO = "ProdutoImagem.listarPorIdProduto";
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqprodutoimagem")
    @Column(name = "idprodutoimagem", unique = true, nullable = false)
    private Long id;

	@OneToOne
    @JoinColumn(name = "idproduto")
	private Produto produto;
	
	@Column(name = "dscaminhoimagem")
	private String descCaminhoImagem;
	
	private boolean bolPadrao;
	
	public ProdutoImagem() {
		
	}
	
	public ProdutoImagem(String descCaminhoImagem) {
		this.descCaminhoImagem = descCaminhoImagem;
	}

	@Override
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getDescCaminhoImagem() {
		return descCaminhoImagem;
	}

	public void setDescCaminhoImagem(String descCaminhoImagem) {
		this.descCaminhoImagem = descCaminhoImagem;
	}

	public boolean isBolPadrao() {
		return bolPadrao;
	}

	public void setBolPadrao(boolean bolPadrao) {
		this.bolPadrao = bolPadrao;
	}

}
