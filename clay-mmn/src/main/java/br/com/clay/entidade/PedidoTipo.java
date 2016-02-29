/**
 * Projeto:         Clay Cosméticos
 * Camada Projeto:  clay-mmn
 * Pacote:          br.com.clay.entidade
 * Arquivo:         PedidoTipo.java
 * Data Criação:    25/02/2016
 */
package br.com.clay.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PedidoTipo
 * 
 * @author Rafael.Silva
 */
@Entity
@Table(name = "PedidoTipo")
public class PedidoTipo extends ClayEntidade {

    private static final long serialVersionUID = -6751034595445330176L;

    public static final short ASSINATURA = 1;
    public static final short COMPRA = 2;

    public PedidoTipo() {
    }

    public PedidoTipo(short id) {
        this.id = id;
    }

    @Id
    @Column(name = "idPedidoTipo", nullable = false, unique = true)
    private Short id;

    @Column(name = "dsPedidoTipo", nullable = false)
    private String descPedidoTipo;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescPedidoTipo() {
        return descPedidoTipo;
    }

    public void setDescPedidoTipo(String descPedidoTipo) {
        this.descPedidoTipo = descPedidoTipo;
    }

}
