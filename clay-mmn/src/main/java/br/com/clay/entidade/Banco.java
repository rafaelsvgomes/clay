package br.com.clay.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Banco
 *
 */
@Entity
@Table(name = "Banco")
public class Banco extends ClayEntidade {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idBanco", unique = true, nullable = false)
    private Short id;

    @Column(name = "dsBanco", nullable = false)
    private String descBanco;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescBanco() {
        return descBanco;
    }

    public void setDescBanco(String descBanco) {
        this.descBanco = descBanco;
    }

}
