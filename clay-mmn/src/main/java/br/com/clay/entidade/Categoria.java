package br.com.clay.entidade;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria extends ClayEntidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idcategoria")
	private Long id;

	private String dsCategoria;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="idcategoriapai")
	private Categoria categoriaPai;

	//bi-directional many-to-one association to Categoria
	@OneToMany(mappedBy="categoriaPai")
	private List<Categoria> categorias;

	@OneToMany(mappedBy="categoria")
	private List<Produto> produtos;

	public Categoria() {
	}
	
	public void setIdcategoria(Long idcategoria) {
		this.id = idcategoria;
	}

	public String getDsCategoria() {
		return this.dsCategoria;
	}

	public void setDsCategoria(String dsCategoria) {
		this.dsCategoria = dsCategoria;
	}

	public Categoria getCategoria() {
		return this.categoriaPai;
	}

	public void setCategoria(Categoria categoria) {
		this.categoriaPai = categoria;
	}

	public List<Categoria> getCategorias() {
		return this.categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Categoria addCategoria(Categoria categoria) {
		getCategorias().add(categoria);
		categoria.setCategoria(this);

		return categoria;
	}

	public Categoria removeCategoria(Categoria categoria) {
		getCategorias().remove(categoria);
		categoria.setCategoria(null);

		return categoria;
	}

	public List<Produto> getProdutos() {
		return this.produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto addProduto(Produto produto) {
		if(getProdutos() == null) {
			setProdutos(new ArrayList<Produto>());
		}
		getProdutos().add(produto);
		produto.setCategoria(this);

		return produto;
	}

	public Produto removeProduto(Produto produto) {
		getProdutos().remove(produto);
		produto.setCategoria(null);

		return produto;
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
        Categoria other = (Categoria) obj;
        if (id == null) {
            if (other.getId() != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        return true;
    }
    
}