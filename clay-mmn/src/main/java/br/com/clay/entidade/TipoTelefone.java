package br.com.clay.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIPOTELEFONE")
public class TipoTelefone extends ClayEntidade {
    private static final long serialVersionUID = -4114897303647563942L;

    public static final Short RESIDENCIAL = 1;
    public static final Short CELULAR = 2;

    public TipoTelefone() {
    }

    public TipoTelefone(Short idTipoTelefone) {
        this.id = idTipoTelefone;
    }

    @Id
    @Column(name = "IDTIPOTELEFONE", unique = true, nullable = false)
    private Short id;

    @Column(name = "dsTipoTelefone", nullable = false)
    private String descTipoTelefone;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescTipoTelefone() {
        return descTipoTelefone;
    }

    public void setDescTipoTelefone(String descTipoTelefone) {
        this.descTipoTelefone = descTipoTelefone;
    }

}
