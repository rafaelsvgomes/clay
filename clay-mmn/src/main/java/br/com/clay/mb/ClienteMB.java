package br.com.clay.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.clay.entidade.Banco;
import br.com.clay.entidade.Cliente;
import br.com.clay.entidade.Endereco;
import br.com.clay.entidade.Pessoa;
import br.com.clay.entidade.PessoaConta;
import br.com.clay.entidade.PlanoAssinatura;
import br.com.clay.entidade.SituacaoCliente;
import br.com.clay.entidade.Telefone;
import br.com.clay.entidade.TipoConta;
import br.com.clay.entidade.TipoEndereco;
import br.com.clay.entidade.TipoTelefone;
import br.com.clay.entidade.UF;
import br.com.clay.servico.ClienteServicoEJB;
import br.com.clay.util.MensagemUtil;

@ManagedBean(name = "clienteMB")
@SessionScoped
public class ClienteMB extends ClayMB {
    private static final long serialVersionUID = -6556028968452915346L;

    @EJB
    ClienteServicoEJB ejb;

    private Long idSelecionado;

    private Cliente cliente;

    private Endereco endereco;

    private Telefone telefone;

    private Telefone celular;

    private PessoaConta pessoaConta;

    private List<Cliente> clientes;

    private List<UF> listaUfs;

    private List<Banco> listaBancos;

    private List<TipoConta> listaTipoConta;

    private List<PlanoAssinatura> listaPlanoAssinatura;

    public ClienteMB() {
    }

    public void incluir() {
        // if (FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) {
        // return; // Skip ajax requests.
        // }
        if (!FacesContext.getCurrentInstance().isPostback()) {
            cliente = new Cliente();
            setTelefonePessoa();
            setEnderecoPessoa();
            setPessoaConta();
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
        telefone.setPessoa(cliente);
        celular = new Telefone();
        celular.setPessoa(cliente);

        telefone.setTipoTelefone(new TipoTelefone(TipoTelefone.RESIDENCIAL));
        celular.setTipoTelefone(new TipoTelefone(TipoTelefone.CELULAR));

        cliente.setListaTelefone(new ArrayList<Telefone>());
        cliente.getListaTelefone().add(telefone);
        cliente.getListaTelefone().add(celular);
    }

    private void setPessoaConta() {
        pessoaConta = new PessoaConta();
        pessoaConta.setPessoa(cliente);
        pessoaConta.setBanco(new Banco());
        pessoaConta.setTipoConta(new TipoConta());
        pessoaConta.setBolContaPrincipal(Boolean.TRUE);
        cliente.setListaPessoaConta(new ArrayList<PessoaConta>());
        cliente.getListaPessoaConta().add(pessoaConta);
    }

    public void editar() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            if (idSelecionado == null) {
                return;
            }
            cliente = ejb.obterPessoa(idSelecionado);
            endereco = cliente.getListaEndereco().get(0);
            pessoaConta = cliente.getListaPessoaConta().get(0);

            for (Telefone tel : cliente.getListaTelefone()) {
                if (tel.getTipoTelefone().getId().equals(TipoTelefone.RESIDENCIAL)) {
                    telefone = tel;
                } else {
                    celular = tel;
                }
            }
        }
    }

    public String salvar() {
        try {
            cliente.setDataCadastro(new Date());

            // TODO: rafael - Substituir replaces por Validator
            cliente.setNumCpfCnpj(cliente.getNumCpfCnpj().replace("-", "").replace(".", "").replace("/", ""));
            cliente.getListaEndereco().get(0).setNumCep(cliente.getListaEndereco().get(0).getNumCep().replace("-", ""));

            cliente.setSituacaoCliente(new SituacaoCliente(1l));
            ejb.save(cliente);
        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemUtil.addMensagem("msg.erro.salvar.cliente", ex.getMessage());
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
            MensagemUtil.addMensagem("msg.erro.remover.cliente", ex.getMessage());
            return "";
        }
        return "lista_cliente";
    }

    // TODO: rafael - Verificar motivo de estar chamando duas vezes a listar
    public List<Cliente> getClientes() {
        // if (!FacesContext.getCurrentInstance().isPostback() || clientes == null) {
        clientes = ejb.findAll();
        // }
        return clientes;
    }

    @SuppressWarnings("unchecked")
    public List<UF> getListaUfs() {
        if (listaUfs == null) {
            listaUfs = ejb.findAll(UF.class);
        }
        return listaUfs;
    }

    @SuppressWarnings("unchecked")
    public List<Banco> getListaBancos() {
        if (listaBancos == null) {
            listaBancos = ejb.findAll(Banco.class);
        }
        return listaBancos;
    }

    @SuppressWarnings("unchecked")
    public List<TipoConta> getListaTipoConta() {
        if (listaTipoConta == null) {
            listaTipoConta = ejb.findAll(TipoConta.class);
        }
        return listaTipoConta;
    }

    @SuppressWarnings("unchecked")
    public List<PlanoAssinatura> getListaPlanoAssinatura() {
        if (listaPlanoAssinatura == null) {
            listaPlanoAssinatura = ejb.findAll(PlanoAssinatura.class);
        }
        return listaPlanoAssinatura;
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

    public PessoaConta getPessoaConta() {
        return pessoaConta;
    }

}
