package br.com.clay.mb;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.clay.entidade.Pessoa;
import br.com.clay.servico.ClienteServicoEJB;

@ManagedBean(name = "clienteMB")
@SessionScoped
public class Cliente {
	
	@EJB
	ClienteServicoEJB ejb;

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
		cliente = ejb.find(idSelecionado);
		
		System.out.println("Cliente recuperado. Id: "+ cliente.getId().toString());
	}

	public String salvar() {
		try {
			cliente.setTipoPessoa(1);
			ejb.save(cliente);
		} catch (Exception ex) {
			ex.printStackTrace();
			addMessage(getMessageFromI18N("msg.erro.salvar.cliente"), ex.getMessage());
			return "";
		}

		return "lista_cliente";
	}

	public String remover() {
		try {
			ejb.remove(cliente);
			System.out.println("Cliente removido");
		} catch (Exception ex) {
			addMessage(getMessageFromI18N("msg.erro.remover.cliente"), ex.getMessage());
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
		ResourceBundle bundle = ResourceBundle.getBundle("messages_labels", getCurrentInstance().getViewRoot().getLocale());
		return bundle.getString(key);
	}

	/**
	 * Adiciona um mensagem no contexto do Faces (<code>FacesContext</code>).
	 * 
	 * @param summary
	 * @param detail
	 */
	private void addMessage(String summary, String detail) {
		getCurrentInstance().addMessage(null, new FacesMessage(summary, summary.concat("<br/>").concat(detail)));
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
		clientes = ejb.findAll();
		return clientes;
	}

}
