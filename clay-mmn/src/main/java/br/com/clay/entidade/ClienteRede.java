package br.com.clay.entidade;

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
 * Entity implementation class for Entity: ClienteRede
 *
 */
@Entity
@Table(name = "CLIENTEREDE")
@SequenceGenerator(name = "seqpessoarede", sequenceName = "seqpessoarede", allocationSize = 1)
public class ClienteRede extends ClayEntidade {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqpessoarede")
    @Column(name = "idClienteRede", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false, updatable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = true, insertable = false, updatable = false)
    private Cliente clientePai;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = true, insertable = false, updatable = false)
    private Cliente clienteIndicador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getClientePai() {
        return clientePai;
    }

    public void setClientePai(Cliente clientePai) {
        this.clientePai = clientePai;
    }

    public Cliente getClienteIndicador() {
        return clienteIndicador;
    }

    public void setClienteIndicador(Cliente clienteIndicador) {
        this.clienteIndicador = clienteIndicador;
    }
}
