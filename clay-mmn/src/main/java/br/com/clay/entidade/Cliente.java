package br.com.clay.entidade;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<ClienteRede> listaClienteRede;

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

    public List<ClienteRede> getListaClienteRede() {
        return listaClienteRede;
    }

    public void setListaClienteRede(List<ClienteRede> listaClienteRede) {
        this.listaClienteRede = listaClienteRede;
    }

    public ClienteSituacao getClienteSituacao() {
        return clienteSituacao;
    }

    public void setClienteSituacao(ClienteSituacao clienteSituacao) {
        this.clienteSituacao = clienteSituacao;
    }

}
