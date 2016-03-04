/**
 * Projeto:         Clay Cosm�ticos
 * Camada Projeto:  clay-mmn
 * Pacote:          br.com.clay.mb
 * Arquivo:         FornecedorMB.java
 * Data Cria��o:    10 de out de 2015
 */
package br.com.clay.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.clay.entidade.Pedido;
import br.com.clay.servico.PedidoServicoEJB;


/**
 * PedidoMB é responsável por 
 * 
 * @author George
 */
@ManagedBean(name = "pedidoMB")
@ViewScoped
public class PedidoMB extends ClayMB {
    private static final long serialVersionUID = -3619457549690706465L;

    private static final String LISTA_PEDIDO = "lista_pedido?faces-redirect=true";

    @EJB
    PedidoServicoEJB ejb;

    private Long idSelecionado;
    private List<Pedido> listaPedidos;
    private Pedido pedido;

    public PedidoMB() {
    }
    
    public void iniciarListarPedidos() {
        if (!isPostBack()) {
           setListaPedidos(ejb.findAll());
        }
    }

    public void editar() {
        if (!isPostBack()) {
            if (idSelecionado == null) {
                return;
            }
            pedido = ejb.find(idSelecionado);
        }
    }
    
    public void incluir() {
        if (!isPostBack()) {
            pedido = new Pedido();
        }
    }
    
    public Long getIdSelecionado() {
        return idSelecionado;
    }

    public void setIdSelecionado(Long idSelecionado) {
        this.idSelecionado = idSelecionado;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
}
