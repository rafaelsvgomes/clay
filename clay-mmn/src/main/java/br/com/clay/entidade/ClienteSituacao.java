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
@Table(name = "ClienteSituacao")
public class ClienteSituacao extends ClayEntidade {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idClienteSituacao", nullable = false, unique = true)
    private Long id;

    @Column(name = "dsClienteSituacao", nullable = false)
    private String descClienteSituacao;

    public ClienteSituacao(Long id) {
        this.id = id;
    }

    public ClienteSituacao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescClienteSituacao() {
        return descClienteSituacao;
    }

    public void setDescClienteSituacao(String descClienteSituacao) {
        this.descClienteSituacao = descClienteSituacao;
    }

}
