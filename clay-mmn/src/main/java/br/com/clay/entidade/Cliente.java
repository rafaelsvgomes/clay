package br.com.clay.entidade;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Entity implementation class for Entity: Cliente
 *
 */
@Entity
@PrimaryKeyJoinColumn(name = "idCliente", referencedColumnName = "idPessoa")
@NamedQueries({ @NamedQuery(name = Cliente.OBTER_POR_DESC_USUARIO, query = "SELECT c.id FROM Cliente c WHERE c.descEmail = :descUsuario"),
        @NamedQuery(name = Cliente.OBTER_POR_NUM_CPF_CNPJ, query = "SELECT c.id FROM Cliente c WHERE c.numCpfCnpj = :numCpfCnpj") })
public class Cliente extends Pessoa {
    private static final long serialVersionUID = 1L;

    public static final String OBTER_POR_DESC_USUARIO = "obterPorDescUsuario";
    public static final String OBTER_POR_NUM_CPF_CNPJ = "obterPorNumCpfCnpj";

    public Cliente(Long codIndicador) {
        super();
        this.id = codIndicador;
    }

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

    public PlanoAssinatura getPlanoAssinatura() {
        return planoAssinatura;
    }

    public void setPlanoAssinatura(PlanoAssinatura planoAssinatura) {
        this.planoAssinatura = planoAssinatura;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     * 
     * Sobrescrevendo para ser encontrado via converter
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     * 
     * Sobrescrevendo para ser encontrado via converter
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        if (id == null) {
            if (other.getId() != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        return true;
    }

}
