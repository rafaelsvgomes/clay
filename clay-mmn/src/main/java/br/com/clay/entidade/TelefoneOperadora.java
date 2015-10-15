package br.com.clay.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: TelefoneOperadora
 *
 */
@Entity
@Table(name = "TELEFONEOPERADORA")
public class TelefoneOperadora extends ClayEntidade {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "IDTELEFONEOPERADORA", unique = true, nullable = false)
    private Short id;

    @Column(name = "dsTelefoneOperadora", nullable = false)
    private String descTelefoneOperadora;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescTelefoneOperadora() {
        return descTelefoneOperadora;
    }

    public void setDescTelefoneOperadora(String descTelefoneOperadora) {
        this.descTelefoneOperadora = descTelefoneOperadora;
    }
}
