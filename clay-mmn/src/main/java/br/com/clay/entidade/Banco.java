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
    @Column(name = "codBanco", unique = true, nullable = false)
    private Short codBanco;

    @Column(name = "dsBanco", nullable = false)
    private String descBanco;

    public String getDescBanco() {
        return descBanco;
    }

    public void setDescBanco(String descBanco) {
        this.descBanco = descBanco;
    }

    public Short getCodBanco() {
        return codBanco;
    }

    public void setCodBanco(Short codBanco) {
        this.codBanco = codBanco;
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.clay.entidade.ClayEntidade#getId()
     */
    @Override
    public Number getId() {
        return null;
    }

}
