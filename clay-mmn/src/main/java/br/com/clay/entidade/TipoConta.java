package br.com.clay.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: TipoConta
 *
 */
@Entity
@Table(name = "TipoConta")
public class TipoConta extends ClayEntidade {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idTipoConta", unique = true, nullable = false)
    private Short id;

    @Column(name = "dsTipoConta", nullable = false)
    private String descTipoConta;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescTipoConta() {
        return descTipoConta;
    }

    public void setDescTipoConta(String descTipoConta) {
        this.descTipoConta = descTipoConta;
    }

}
