package br.com.clay.entidade;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Entity implementation class for Entity: Cliente
 *
 */
@Entity
@PrimaryKeyJoinColumn(name = "idCliente", referencedColumnName = "idPessoa")
public class Cliente extends Pessoa {
    private static final long serialVersionUID = 1L;

    /**
     * @param codIndicador
     */
    public Cliente(Long codIndicador) {
        super();
        this.id = codIndicador;
    }

    /**
     * 
     */
    public Cliente() {
    }

    @ManyToOne
    @JoinColumn(name = "idPlanoAssinatura")
    private PlanoAssinatura planoAssinatura;

    @ManyToOne
    @JoinColumn(name = "idClienteSituacao", nullable = true)
    private ClienteSituacao clienteSituacao;

    @OneToOne(optional = true, mappedBy = "cliente", cascade = CascadeType.ALL)
    private ClienteRede clienteRede;

    private Date dataAtualizacao;

    public PlanoAssinatura getPlanoAssinatura() {
        return planoAssinatura;
    }

    public void setPlanoAssinatura(PlanoAssinatura planoAssinatura) {
        this.planoAssinatura = planoAssinatura;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public ClienteSituacao getClienteSituacao() {
        return clienteSituacao;
    }

    public void setClienteSituacao(ClienteSituacao clienteSituacao) {
        this.clienteSituacao = clienteSituacao;
    }

    public ClienteRede getClienteRede() {
        return this.clienteRede;
    }

    public void setClienteRede(ClienteRede clienteRede) {
        this.clienteRede = clienteRede;
    }

}
