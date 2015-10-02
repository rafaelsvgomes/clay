package br.com.clay.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UF")
public class UF {
	
	@Id
    @Column(name = "IDUF", unique = true, nullable = false)
	private Short id;
	
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
