/**
 * Projeto:         Clay Cosméticos
 * Camada Projeto:  clay-mmn
 * Pacote:          br.com.clay.entidade
 * Arquivo:         StatusPagamento.java
 * Data Criação:    29/02/2016
 */
package br.com.clay.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * StatusPagamento
 * 
 * @author Rafael.Silva
 */
@Entity
@Table(name = "StatusPagamento")
public class StatusPagamento extends ClayEntidade {
    private static final long serialVersionUID = 8411305991901969971L;

    public static final short AGUARDANDO_PAGAMENTO = 1;
    public static final short EM_ANALISE = 2;
    public static final short PAGO = 3;
    public static final short DISPONIVEL = 4;
    public static final short EM_DISPUTA = 5;
    public static final short DEVOLVIDA = 6;
    public static final short CANCELADA = 7;

    public StatusPagamento() {
    }

    public StatusPagamento(short id) {
        this.id = id;
    }

    @Id
    @Column(name = "idStatusPagamento", nullable = false, unique = true)
    private Short id;

    @Column(name = "dsStatusPagamento", nullable = false)
    private String descStatusPagamento;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescStatusPagamento() {
        return descStatusPagamento;
    }

    public void setDescStatusPagamento(String descStatusPagamento) {
        this.descStatusPagamento = descStatusPagamento;
    }

}
