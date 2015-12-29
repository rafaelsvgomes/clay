package br.com.clay.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: SituacaoCliente
 *
 */
@Entity
@Table(name = "SituacaoCliente")
public class SituacaoCliente extends ClayEntidade {
    private static final long serialVersionUID = 1L;

    public static final long CADASTRADO = 1;
    public static final long ATIVO = 2;
    public static final long INATIVO = 3;
    public static final long BLOQUEADO = 4;

    @Id
    @Column(name = "idSituacaoCliente", nullable = false, unique = true)
    private Long id;

    @Column(name = "dsSituacaoCliente", nullable = false)
    private String descSituacaoCliente;

    /**
     * @param i
     */
    public SituacaoCliente(Long id) {
        this.id = id;
    }

    /**
     * 
     */
    public SituacaoCliente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescSituacaoCliente() {
        return descSituacaoCliente;
    }

    public void setDescSituacaoCliente(String descSituacaoCliente) {
        this.descSituacaoCliente = descSituacaoCliente;
    }

}
