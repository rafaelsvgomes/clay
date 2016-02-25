/**
 * Projeto:         Clay Cosméticos
 * Camada Projeto:  clay-mmn
 * Pacote:          br.com.clay.entidade
 * Arquivo:         OrigemPagamento.java
 * Data Criação:    25/02/2016
 */
package br.com.clay.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OrigemPagamento
 * 
 * @author Rafael.Silva
 */
@Entity
@Table(name = "OrigemPagamento")
public class OrigemPagamento extends ClayEntidade {

    private static final long serialVersionUID = 919101219244401727L;

    @Id
    @Column(name = "idOrigemPagamento", unique = true, nullable = false)
    private Short id;

    @Column(name = "dsOrigemPagamento", nullable = false)
    private String descOrigemPagamento;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescOrigemPagamento() {
        return descOrigemPagamento;
    }

    public void setDescOrigemPagamento(String descOrigemPagamento) {
        this.descOrigemPagamento = descOrigemPagamento;
    }
}
