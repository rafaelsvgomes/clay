package br.com.clay.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIPOENDERECO")
public class TipoEndereco {

	@Id
    @Column(name = "IDTIPOENDERECO", unique = true, nullable = false)
	private Short id;
	
	private String descTipoEndereco;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getDescTipoEndereco() {
		return descTipoEndereco;
	}

	public void setDescTipoEndereco(String descTipoEndereco) {
		this.descTipoEndereco = descTipoEndereco;
	}
}
