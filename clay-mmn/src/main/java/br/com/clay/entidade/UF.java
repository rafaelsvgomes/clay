package br.com.clay.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "UF")
@NamedQueries( { 
	@NamedQuery(name = UF.LISTAR, query = "SELECT u FROM UF u")
	})
public class UF implements ClayEntidade {
	private static final long serialVersionUID = -8342220191530234596L;

	public static final String LISTAR = "UF.Listar";
	
	@Id
    @Column(name = "IDUF", unique = true, nullable = false)
	private Short id;
	
	@Column(name = "dsuf")
	private String descUf;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getDescUf() {
		return descUf;
	}

	public void setDescUf(String descUf) {
		this.descUf = descUf;
	}

}
