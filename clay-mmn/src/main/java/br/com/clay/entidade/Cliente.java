package br.com.clay.entidade;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Entity implementation class for Entity: Cliente
 *
 */
@Entity
@PrimaryKeyJoinColumn(name = "idCliente", referencedColumnName = "idPessoa")
public class Cliente extends Pessoa {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "idPlanoAssinatura")
    private PlanoAssinatura planoAssinatura;

    @ManyToOne
    @JoinColumn(name = "idSituacaoCliente", nullable = true)
    private SituacaoCliente situacaoCliente;

    public PlanoAssinatura getPlanoAssinatura() {
        return planoAssinatura;
    }

    public void setPlanoAssinatura(PlanoAssinatura planoAssinatura) {
        this.planoAssinatura = planoAssinatura;
    }

    public SituacaoCliente getSituacaoCliente() {
        return situacaoCliente;
    }

    public void setSituacaoCliente(SituacaoCliente situacaoCliente) {
        this.situacaoCliente = situacaoCliente;
    }

}
