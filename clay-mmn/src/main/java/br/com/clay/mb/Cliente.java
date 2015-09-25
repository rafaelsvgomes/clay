package br.com.clay.mb;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.com.clay.entidade.Pessoa;

@ManagedBean(name = "clienteMB")
@SessionScoped
public class Cliente implements Serializable {

//	@PersistenceContext(unitName = "clay_pu")
	EntityManager em;

	
	@EJB
	ClienteEJB clienteEJB;

	private Long idSelecionado;

	private Pessoa cliente;

	private List<Pessoa> clientes;

	public Cliente() {
	}

	public void inlcuir() {
		cliente = new Pessoa();
	}

	public void editar() {
		if (idSelecionado == null) {
			return;
		}
		cliente = em.find(Pessoa.class, idSelecionado);
	}

	public String salvar() {
		try {
			clienteEJB.salvar(cliente);
		} catch (Exception ex) {
			ex.printStackTrace();
			addMessage(getMessageFromI18N("msg.erro.salvar.cliente"),
					ex.getMessage());
			return "";
		}

		return "lista_cliente";
	}

	public String remover() {
		try {
			em.remove(cliente);
		} catch (Exception ex) {
			addMessage(getMessageFromI18N("msg.erro.remover.cliente"),
					ex.getMessage());
			return "";
		}
		return "lista_cliente";
	}

	/**
	 * @param key
	 * @return Recupera a mensagem do arquivo properties
	 *         <code>ResourceBundle</code>.
	 */
	private String getMessageFromI18N(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_labels",
				getCurrentInstance().getViewRoot().getLocale());
		return bundle.getString(key);
	}

	/**
	 * Adiciona um mensagem no contexto do Faces (<code>FacesContext</code>).
	 * 
	 * @param summary
	 * @param detail
	 */
	private void addMessage(String summary, String detail) {
		getCurrentInstance().addMessage(
				null,
				new FacesMessage(summary, summary.concat("<br/>")
						.concat(detail)));
	}

	public Long getIdSelecionado() {
		return idSelecionado;
	}

	public void setIdSelecionado(Long idSelecionado) {
		this.idSelecionado = idSelecionado;
	}

	public Pessoa getCliente() {
		return cliente;
	}

	public List<Pessoa> getClientes() {
//		if(clientes == null){
			clientes = clienteEJB.getClientes(); 
//		}
		return clientes;
	}

}
