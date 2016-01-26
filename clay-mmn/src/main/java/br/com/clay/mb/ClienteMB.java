package br.com.clay.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;

import br.com.clay.entidade.Banco;
import br.com.clay.entidade.Cliente;
import br.com.clay.entidade.ClienteRede;
import br.com.clay.entidade.ClienteSituacao;
import br.com.clay.entidade.Grupo;
import br.com.clay.entidade.Pessoa;
import br.com.clay.entidade.PessoaConta;
import br.com.clay.entidade.PessoaEndereco;
import br.com.clay.entidade.PessoaTelefone;
import br.com.clay.entidade.PlanoAssinatura;
import br.com.clay.entidade.TipoConta;
import br.com.clay.entidade.TipoEndereco;
import br.com.clay.entidade.TipoTelefone;
import br.com.clay.entidade.UF;
import br.com.clay.entidade.Usuario;
import br.com.clay.entidade.UsuarioGrupo;
import br.com.clay.entidade.UsuarioPessoa;
import br.com.clay.enums.TipoPessoa;
import br.com.clay.exception.NegocioException;
import br.com.clay.servico.ClienteServicoEJB;
import br.com.clay.util.Email;
import br.com.clay.util.EmailUtil;
import br.com.clay.util.MensagemUtil;
import br.com.clay.util.SenhaUtil;

@ManagedBean(name = "clienteMB")
@SessionScoped
public class ClienteMB extends ClayMB {
    private static final long serialVersionUID = -6556028968452915346L;

    @EJB
    ClienteServicoEJB ejb;

    private Long idSelecionado;

    private Cliente cliente;

    private PessoaEndereco endereco;

    private PessoaTelefone telefone;

    private PessoaTelefone celular;

    private PessoaConta pessoaConta;

    private List<Cliente> clientes;

    private List<UF> listaUfs;

    private List<Banco> listaBancos;

    private List<TipoConta> listaTipoConta;

    private List<PlanoAssinatura> listaPlanoAssinatura;

    private Long codIndicador;

    private Boolean tabPagamento = Boolean.FALSE;

    public ClienteMB() {
    }

    public void incluir() {
        // if (FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) {
        // return; // Skip ajax requests.
        // }
        if (!FacesContext.getCurrentInstance().isPostback()) {
            cliente = new Cliente();
            cliente.setTipoPessoa(TipoPessoa.F);
            cliente.setClienteSituacao(new ClienteSituacao(ClienteSituacao.CADASTRADO));
            setTelefonePessoa();
            setEnderecoPessoa();
            setPessoaConta();
            codIndicador = null;

            ClienteRede clienteRede = new ClienteRede();
            clienteRede.setCliente(cliente);
            cliente.setClienteRede(clienteRede);
        }
    }

    public void editar() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            if (idSelecionado == null) {
                return;
            }
            cliente = ejb.obterPessoa(idSelecionado);
            endereco = cliente.getListaEndereco().get(0);
            pessoaConta = cliente.getListaPessoaConta().get(0);
            codIndicador = null;

            for (PessoaTelefone tel : cliente.getListaTelefone()) {
                if (tel.getTipoTelefone().getId().equals(TipoTelefone.RESIDENCIAL)) {
                    telefone = tel;
                } else {
                    celular = tel;
                }
            }

            if (cliente.getClienteRede() != null && cliente.getClienteRede().getClienteIndicador() != null) {
                codIndicador = cliente.getClienteRede().getClienteIndicador().getId();
            }
        }
    }

    private void setEnderecoPessoa() {
        endereco = new PessoaEndereco();
        endereco.setPessoa(cliente);

        endereco.setTipoEndereco(new TipoEndereco(TipoEndereco.RESIDENCIAL));
        cliente.setListaEndereco(new ArrayList<PessoaEndereco>());
        cliente.getListaEndereco().add(endereco);
    }

    private void setTelefonePessoa() {
        telefone = new PessoaTelefone();
        telefone.setPessoa(cliente);
        celular = new PessoaTelefone();
        celular.setPessoa(cliente);

        telefone.setTipoTelefone(new TipoTelefone(TipoTelefone.RESIDENCIAL));
        celular.setTipoTelefone(new TipoTelefone(TipoTelefone.CELULAR));

        cliente.setListaTelefone(new ArrayList<PessoaTelefone>());
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

    /**
     * 
     * @throws NegocioException
     * 
     */
    private void setClienteRede() throws NegocioException {
        Cliente clienteIndicador = ejb.find(codIndicador);
        if (clienteIndicador == null) {
            throw new NegocioException("Cliente indicador não encontrado");
        }

        cliente.getClienteRede().setClienteIndicador(clienteIndicador);
    }

    // TODO: rafael - ajustar para edição
    private void setUsuario() {
        if (idSelecionado == null) {
            Usuario usuario = new Usuario();
            UsuarioPessoa usuarioPessoa = new UsuarioPessoa();
            UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
            usuarioGrupo.setGrupo(new Grupo(Grupo.USER));

            usuario.setDescUsuario(cliente.getDescEmail());
            usuario.setDescSenha(SenhaUtil.gerarSenhaUsuario(cliente));
            usuario.getListaUsuarioPessoa().add(usuarioPessoa);
            usuario.getListaUsuarioGrupo().add(usuarioGrupo);

            usuarioPessoa.setPessoa(cliente);
            usuarioPessoa.setUsuario(usuario);

            usuarioGrupo.setUsuario(usuario);

            cliente.getListaUsuarioPessoa().add(usuarioPessoa);
        }
    }

    public String salvar() {
        try {
            cliente.setDataCadastro(new Date());

            // TODO: rafael - Substituir replaces por Validator
            cliente.setNumCpfCnpj(cliente.getNumCpfCnpj().replace("-", "").replace(".", "").replace("/", ""));
            cliente.getListaEndereco().get(0).setNumCep(cliente.getListaEndereco().get(0).getNumCep().replace("-", ""));
            cliente.setDataAtualizacao(new Date());

            if (codIndicador != null && !codIndicador.equals(0L)) {
                setClienteRede();
            }
            setUsuario();

            ejb.save(cliente);
        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemUtil.addMensagem("msg.erro.salvar.cliente", ex.getMessage());
            return "";
        } finally {
            try {
                EmailUtil.enviaEmail(getEmailCadastro());
            } catch (Exception emailEx) {
                emailEx.printStackTrace();
                MensagemUtil.addMensagem("msg.cliente.salvo.erro.enviar.email", emailEx.getMessage());
                return "";
            }
        }
        tabPagamento = true;

        return "tabPagamento";
    }

    public String onFlowProcess(FlowEvent event) {
        if (tabPagamento) {
            // skip = false; // reset in case user goes back
            return "tabPagamento";
        } else {
            return event.getNewStep();
        }
    }

    private Email getEmailCadastro() {
        Email mensagem = new Email();
        mensagem.setDestino(cliente.getDescEmail());
        mensagem.setMensagem("Usuario Cadastrado com Sucesso!!! \nUsuario: " + cliente.getDescEmail() + "\nSenha: " + SenhaUtil.getSenhaPadrao(cliente));
        mensagem.setTitulo("Cadastro com sucesso - Claystore");
        return mensagem;
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
    // Ao clicar no botão de proxima pagina esta buscando uma vez a cada item da lista.
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

    public PessoaEndereco getEndereco() {
        return endereco;
    }

    public PessoaTelefone getTelefone() {
        return telefone;
    }

    public String getDDDCelular() {
        return celular.getDescTelefone().substring(0, 2);
    }

    public PessoaTelefone getCelular() {
        return celular;
    }

    public PessoaConta getPessoaConta() {
        return pessoaConta;
    }

    public Long getCodIndicador() {
        return codIndicador;
    }

    public void setCodIndicador(Long codIndicador) {
        this.codIndicador = codIndicador;
    }

    public Boolean getTabPagamento() {
        return tabPagamento;
    }

    public void setTabPagamento(Boolean tabPagamento) {
        this.tabPagamento = tabPagamento;
    }

}
