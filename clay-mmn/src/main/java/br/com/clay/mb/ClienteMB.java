package br.com.clay.mb;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
import br.com.clay.entidade.Produto;
import br.com.clay.entidade.TipoConta;
import br.com.clay.entidade.TipoEndereco;
import br.com.clay.entidade.TipoTelefone;
import br.com.clay.entidade.UF;
import br.com.clay.entidade.Usuario;
import br.com.clay.entidade.UsuarioGrupo;
import br.com.clay.entidade.UsuarioPessoa;
import br.com.clay.enums.TipoPessoa;
import br.com.clay.servico.ClienteServicoEJB;
import br.com.clay.util.Email;
import br.com.clay.util.EmailUtil;
import br.com.clay.util.MensagemUtil;
import br.com.clay.util.SenhaUtil;

@ManagedBean(name = "clienteMB")
@ViewScoped
public class ClienteMB extends ClayMB {
    private static final long serialVersionUID = -6556028968452915346L;

    @EJB
    private ClienteServicoEJB ejb;

    private Long idSelecionado;

    private Cliente cliente;

    private PessoaEndereco endereco;

    private PessoaTelefone telefone;

    private PessoaTelefone celular;

    private PessoaConta pessoaConta;

    private List<Cliente> listaClientes;

    private List<UF> listaUfs;

    private List<Banco> listaBancos;

    private List<TipoConta> listaTipoConta;

    private List<PlanoAssinatura> listaPlanoAssinatura;

    private Cliente clienteIndicador;

    private List<Produto> listaProdutosPlanoAssinatura;

    private String reEmail;

    public ClienteMB() {
    }

    public void incluir() {
        // if (FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) {
        // return; // Skip ajax requests.
        // }
        if (!FacesContext.getCurrentInstance().isPostback()) {
            idSelecionado = null;

            cliente = new Cliente();
            cliente.setTipoPessoa(TipoPessoa.F);
            cliente.setClienteSituacao(new ClienteSituacao(ClienteSituacao.CADASTRADO));
            setTelefonePessoa();
            setEnderecoPessoa();
            setPessoaConta();

            clienteIndicador = ejb.obterCliente(getUsuarioLogado().getIdCliente());

            ClienteRede clienteRede = new ClienteRede();
            clienteRede.setClienteIndicador(clienteIndicador);
            clienteRede.setCliente(cliente);
            cliente.setClienteRede(clienteRede);

            iniciarListarClientes();
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

            if (cliente.getClienteRede() != null) {
                clienteIndicador = cliente.getClienteRede().getClienteIndicador();

                listaClientes = new ArrayList<Cliente>();
                listaClientes.add(clienteIndicador);
            }

            for (PessoaTelefone tel : cliente.getListaTelefone()) {
                if (tel.getTipoTelefone().getId().equals(TipoTelefone.RESIDENCIAL)) {
                    telefone = tel;
                } else {
                    celular = tel;
                }
            }
        }
    }

    public void iniciarPagamento() {
        // TODO: rafael
        // Na hora de gravar salva a alteração efetuada no plano (Ou pode deixar pra alterar só no cadastro) chama o pagamento.
        // Implementar ativar com botão no listar se for grupo admin.
        if (!FacesContext.getCurrentInstance().isPostback()) {
            cliente = ejb.obterCliente(getUsuarioLogado().getIdCliente());
        }
    }

    public String ativarCliente(Long idCliente) {
        try {
            cliente = ejb.obterPessoa(idCliente);
            cliente.setClienteSituacao(new ClienteSituacao(ClienteSituacao.ATIVO));
            ejb.save(cliente);

            MensagemUtil.addMensagemSucesso("msg.sucesso.ativar.cliente");
        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemUtil.addMensagemErro("msg.erro.salvar.cliente", ex.getMessage());
        }
        return "lista_cliente?faces-redirect=true";
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

    // TODO: rafael - ajustar para ediçõa
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
        Boolean enviarEmail = Boolean.FALSE;
        try {
            if (cliente.getId() == null || cliente.getId() == 0) {
                cliente.setDataCadastro(new Date());
                cliente.setDataAtualizacao(new Date());
                enviarEmail = Boolean.TRUE;
            } else {
                cliente.setDataAtualizacao(new Date());
            }

            // TODO: rafael - Substituir replaces por Validator
            cliente.setNumCpfCnpj(cliente.getNumCpfCnpj().replace("-", "").replace(".", "").replace("/", ""));
            cliente.getListaEndereco().get(0).setNumCep(cliente.getListaEndereco().get(0).getNumCep().replace("-", ""));

            setUsuario();

            ejb.save(cliente);
        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemUtil.addMensagemErro("msg.erro.salvar.cliente", ex.getMessage());
            return "";
        } finally {
            // TODO: rafael - ajustar lógica de enviar email e salvar sincronamente
            if (enviarEmail) {
                try {
                    EmailUtil.enviaEmail(getEmailCadastro());
                } catch (Exception emailEx) {
                    emailEx.printStackTrace();
                    MensagemUtil.addMensagemErro("msg.cliente.salvo.erro.enviar.email", emailEx.getMessage());
                }
            }
        }
        MensagemUtil.addMensagemSucesso("msg.sucesso.salvar.cliente");
        return "lista_cliente?faces-redirect=true";
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
            MensagemUtil.addMensagemErro("msg.erro.remover.cliente", ex.getMessage());
            return "";
        }
        return "lista_cliente?faces-redirect=true";
    }

    public void validaEmail() {
        if (!cliente.getDescEmail().equals(reEmail)) {
            // MensagemUtil.addMensagemErro("msg.erro.remover.cliente", "Teste");

            getCurrentInstance().addMessage("id", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Teste", "Teste"));
        }

    }

    public void iniciarListarClientes() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            listaClientes = ejb.findAll();
        }
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void init() {
        // TODO: rafael - Teste de metodos carregados apenas na primeira vez que entra na tela.
        listaUfs = ejb.findAll(UF.class);
        listaBancos = ejb.findAll(Banco.class);
        listaTipoConta = ejb.findAll(TipoConta.class);
        listaPlanoAssinatura = ejb.findAll(PlanoAssinatura.class);
    }

    public List<UF> getListaUfs() {
        return listaUfs;
    }

    public List<Banco> getListaBancos() {
        return listaBancos;
    }

    public List<TipoConta> getListaTipoConta() {
        return listaTipoConta;
    }

    public List<PlanoAssinatura> getListaPlanoAssinatura() {
        return listaPlanoAssinatura;
    }

    public void setListaProdutosPlanoAssinatura() {
        if (cliente.getPlanoAssinatura() != null) {
            listaProdutosPlanoAssinatura = ejb.obterProdutosKit(cliente.getPlanoAssinatura().getProduto().getId());
        }
    }

    public List<Produto> getListaProdutosPlanoAssinatura() {
        if (listaProdutosPlanoAssinatura == null) {
            setListaProdutosPlanoAssinatura();
        }
        return listaProdutosPlanoAssinatura;
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
        for (PessoaTelefone tel : cliente.getListaTelefone()) {
            if (tel.getTipoTelefone().getId().equals(TipoTelefone.CELULAR)) {
                return tel.getDescTelefone().substring(0, 2);
            }
        }
        return null;
    }

    public PessoaTelefone getCelular() {
        return celular;
    }

    public PessoaConta getPessoaConta() {
        return pessoaConta;
    }

    public Cliente getClienteIndicador() {
        return clienteIndicador;
    }

    public String getReEmail() {
        return reEmail;
    }

    public void setReEmail(String reEmail) {
        this.reEmail = reEmail;
    }
}
