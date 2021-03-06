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
@Table(name = "PedidoProdutoSituacao")
public class PedidoProdutoSituacao extends ClayEntidade {

    private static final long serialVersionUID = -5464111484357685263L;

    public static final short ABERTO = 1;
    public static final short APROVADO = 2;

    public PedidoProdutoSituacao() {
    }

    public PedidoProdutoSituacao(short id) {
        this.id = id;
    }

    @Id
    @Column(name = "idPedidoProdutoSituacao", nullable = false, unique = true)
    private Short id;

    @Column(name = "dsPedidoProdutoSituacao")
    private String descPedidoProdutoSituacao;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescPedidoProdutoSituacao() {
        return descPedidoProdutoSituacao;
    }

    public void setDescPedidoProdutoSituacao(String descPedidoProdutoSituacao) {
        this.descPedidoProdutoSituacao = descPedidoProdutoSituacao;
    }

}
