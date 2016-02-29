/**
 * Projeto:         Clay Cosméticos
 * Camada Projeto:  clay-mmn
 * Pacote:          br.com.clay.entidade
 * Arquivo:         PedidoSituacao.java
 * Data Criação:    25/02/2016
 */
package br.com.clay.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PedidoSituacao
 * 
 * @author Rafael.Silva
 */

@Entity
@Table(name = "PedidoSituacao")
public class PedidoSituacao extends ClayEntidade {

    private static final long serialVersionUID = -5464111484357685263L;

    public static final short ABERTO = 1;
    public static final short AGUARDANDO_PAGAMENTO = 2;
    public static final short AGUARDANDO_RETIRADA = 3;
    public static final short FINALIZADO = 4;

    public PedidoSituacao() {
    }

    public PedidoSituacao(short id) {
        this.id = id;
    }

    @Id
    @Column(name = "idPedidoSituacao", nullable = false, unique = true)
    private Short id;

    @Column(name = "dsPedidoSituacao")
    private String descPedidoSituacao;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescPedidoSituacao() {
        return descPedidoSituacao;
    }

    public void setDescPedidoSituacao(String descPedidoSituacao) {
        this.descPedidoSituacao = descPedidoSituacao;
    }

}
