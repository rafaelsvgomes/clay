package br.com.clay.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Telefone
 *
 */
@Entity
@Table(name = "TELEFONE")
@SequenceGenerator(name = "seqtelefone", sequenceName = "seqtelefone", allocationSize = 1)
public class Telefone extends ClayEntidade {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "IDTELEFONE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqtelefone")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idTipoTelefone", nullable = false)
    private TipoTelefone tipoTelefone;

    @ManyToOne
    @JoinColumn(name = "idTelefoneOperadora")
    private TelefoneOperadora telefoneOperadora;

    @ManyToOne
    @JoinColumn(name = "idPessoa", nullable = false)
    private Pessoa pessoa;

    @Column(name = "dsTelefone", nullable = false)
    private String descTelefone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public TelefoneOperadora getTelefoneOperadora() {
        return telefoneOperadora;
    }

    public void setTelefoneOperadora(TelefoneOperadora telefoneOperadora) {
        this.telefoneOperadora = telefoneOperadora;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getDescTelefone() {
        return descTelefone;
    }

    public void setDescTelefone(String descTelefone) {
        this.descTelefone = descTelefone;
    }
}
