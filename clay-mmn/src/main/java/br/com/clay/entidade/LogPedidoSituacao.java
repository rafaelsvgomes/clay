/**
 * Projeto:         Clay Cosméticos
 * Camada Projeto:  clay-mmn
 * Pacote:          br.com.clay.entidade
 * Arquivo:         LogPedidoSituacao.java
 * Data Criação:    29/02/2016
 */
package br.com.clay.entidade;

import java.util.Date;

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
 * LogPedidoSituacao
 * 
 * @author Rafael.Silva
 */
@Entity
@Table(name = "LogPedidoSituacao")
@SequenceGenerator(name = "seqlogpedidosituacao", sequenceName = "seqlogpedidosituacao", allocationSize = 1)
public class LogPedidoSituacao extends ClayEntidade {

    private static final long serialVersionUID = 4021847216441088976L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqlogpedidosituacao")
    @Column(name = "idLogPedidoSituacao", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idStatusPagamento")
    private StatusPagamento statusPagamento;

    @Column(nullable = false)
    private String codTransacao;

    @Column(nullable = false)
    private Date dataHoraPedidoSituacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public String getCodTransacao() {
        return codTransacao;
    }

    public void setCodTransacao(String codTransacao) {
        this.codTransacao = codTransacao;
    }

    public Date getDataHoraPedidoSituacao() {
        return dataHoraPedidoSituacao;
    }

    public void setDataHoraPedidoSituacao(Date dataHoraPedidoSituacao) {
        this.dataHoraPedidoSituacao = dataHoraPedidoSituacao;
    }

}
