package br.com.clay.mb;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.clay.entidade.Endereco;
import br.com.clay.entidade.Pessoa;
import br.com.clay.entidade.Telefone;
import br.com.clay.entidade.TipoEndereco;
import br.com.clay.entidade.TipoTelefone;
import br.com.clay.entidade.UF;
import br.com.clay.servico.ClienteServicoEJB;

@ManagedBean(name = "clienteMB")
@SessionScoped
public class ClienteMB extends ClayMB {
    private static final long serialVersionUID = -6556028968452915346L;

    @EJB
    ClienteServicoEJB ejb;

    private Long idSelecionado;

    private Pessoa cliente;

    private Endereco endereco;

    private Telefone telefone;

    private Telefone celular;

    private List<Pessoa> clientes;

    private List<UF> listaUfs;

    public ClienteMB() {
    }

    public void incluir() {
        // if (FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) {
        // return; // Skip ajax requests.
        // }
        if (!FacesContext.getCurrentInstance().isPostback()) {
            cliente = new Pessoa();
            setEnderecoPessoa();
            setTelefonePessoa();
        }
    }

    private void setEnderecoPessoa() {
        endereco = new Endereco();
        endereco.setPessoa(cliente);

        endereco.setTipoEndereco(new TipoEndereco(TipoEndereco.RESIDENCIAL));
        cliente.setListaEndereco(new ArrayList<Endereco>());
        cliente.getListaEndereco().add(endereco);
    }

    private void setTelefonePessoa() {
        telefone = new Telefone();
        celular = new Telefone();

        telefone.setTipoTelefone(new TipoTelefone(TipoTelefone.RESIDENCIAL));
        celular.setTipoTelefone(new TipoTelefone(TipoTelefone.CELULAR));

        cliente.setListaTelefone(new ArrayList<Telefone>());
        cliente.getListaTelefone().add(telefone);
        cliente.getListaTelefone().add(celular);
    }

    public void editar() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            if (idSelecionado == null) {
                return;
            }
            cliente = ejb.obterPessoa(idSelecionado);
            endereco = cliente.getListaEndereco().get(0);
        }
    }

    public String salvar() {
        try {
            cliente.setDataCadastro(Calendar.getInstance());

            // TODO: rafael - Substituir replaces por Validator
            cliente.setNumCpfCnpj(cliente.getNumCpfCnpj().replace("-", "").replace(".", "").replace("/", ""));
            cliente.getListaEndereco().get(0).setNumCep(cliente.getListaEndereco().get(0).getNumCep().replace("-", ""));

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
            ex.printStackTrace();
            addMessage(getMessageFromI18N("msg.erro.remover.cliente"), ex.getMessage());
            return "";
        }
        return "lista_cliente";
    }

    // TODO: rafael - Verificar motivo de estar chamando duas vezes a listar
    public List<Pessoa> getClientes() {
        if (!FacesContext.getCurrentInstance().isPostback() || clientes == null) {
            clientes = ejb.findAll();
        }
        return clientes;
    }

    /**
     * @param key
     * @return Recupera a mensagem do arquivo properties <code>ResourceBundle</code>.
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

    public List<UF> getListaUfs() {
        if (listaUfs == null) {
            listaUfs = ejb.listarUfs();
        }
        return listaUfs;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public Telefone getCelular() {
        return celular;
    }
}