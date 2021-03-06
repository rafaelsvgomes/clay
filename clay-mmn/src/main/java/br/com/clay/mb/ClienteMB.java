package br.com.clay.mb;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import br.com.clay.entidade.Banco;
import br.com.clay.entidade.Cliente;
import br.com.clay.entidade.ClienteRede;
import br.com.clay.entidade.ClienteSituacao;
import br.com.clay.entidade.Grupo;
import br.com.clay.entidade.LogPedidoSituacao;
import br.com.clay.entidade.OrigemPagamento;
import br.com.clay.entidade.Pedido;
import br.com.clay.entidade.PedidoProduto;
import br.com.clay.entidade.PedidoProdutoSituacao;
import br.com.clay.entidade.PedidoSituacao;
import br.com.clay.entidade.PedidoTipo;
import br.com.clay.entidade.Pessoa;
import br.com.clay.entidade.PessoaConta;
import br.com.clay.entidade.PessoaEndereco;
import br.com.clay.entidade.PessoaTelefone;
import br.com.clay.entidade.PlanoAssinatura;
import br.com.clay.entidade.Produto;
import br.com.clay.entidade.StatusPagamento;
import br.com.clay.entidade.TipoConta;
import br.com.clay.entidade.TipoEndereco;
import br.com.clay.entidade.TipoTelefone;
import br.com.clay.entidade.UF;
import br.com.clay.entidade.Usuario;
import br.com.clay.entidade.UsuarioGrupo;
import br.com.clay.entidade.UsuarioPessoa;
import br.com.clay.enums.TipoPessoa;
import br.com.clay.exception.CEPProxyException;
import br.com.clay.servico.ClienteServicoEJB;
import br.com.clay.util.CpfCnpjUtil;
import br.com.clay.util.Email;
import br.com.clay.util.EmailUtil;
import br.com.clay.util.MensagemUtil;
import br.com.clay.util.SenhaUtil;
import br.com.clay.vo.CepServiceVO;
import br.com.clay.webservices.CepService;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;

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

    private List<Produto> listaProdutosPlanoAssinatura;

    private List<Cliente> listaClientesIndicadores;

    private List<OrigemPagamento> listaOrigemPagamento;

    private Pedido pedido;

    private Boolean podeAlterarPlano;

    private Boolean podeEfetuarPagamento;

    public ClienteMB() {
    }

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void init() {
        listaUfs = ejb.findAll(UF.class);
        listaBancos = ejb.findAll(Banco.class);
        listaTipoConta = ejb.findAll(TipoConta.class);
        listaPlanoAssinatura = ejb.listarPlanoAssinatura();
    }

    public void incluir() {
        if (!isPostBack()) {
            idSelecionado = null;
            podeAlterarPlano = Boolean.TRUE;

            cliente = new Cliente();
            cliente.setTipoPessoa(TipoPessoa.F);
            cliente.setClienteSituacao(new ClienteSituacao(ClienteSituacao.CADASTRADO));
            setTelefonePessoa();
            setEnderecoPessoa();
            setPessoaConta();

            ClienteRede clienteRede = new ClienteRede();
            clienteRede.setClienteIndicador(new Cliente(getUsuarioLogado().getIdCliente(), getUsuarioLogado().getNomePessoa()));
            clienteRede.setCliente(cliente);
            cliente.setClienteRede(clienteRede);

            initListaClienteIndicador();
        }
    }

    public void editar() {
        if (!isPostBack()) {
            podeAlterarPlano = Boolean.TRUE;
            if (idSelecionado == null) {
                return;
            }
            iniciarClienteEditar(idSelecionado);
        }
    }

    private void iniciarClienteEditar(Long idCliente) {
        cliente = ejb.obterPessoa(idCliente);
        endereco = cliente.getListaEndereco().get(0);
        pessoaConta = cliente.getListaPessoaConta().get(0);

        if (cliente.getClienteRede() != null) {
            listaClientesIndicadores = new ArrayList<Cliente>();
            listaClientesIndicadores.add(cliente.getClienteRede().getClienteIndicador());
        }

        listaProdutosPlanoAssinatura = ejb.listarProdutosKit(cliente.getPlanoAssinatura().getProduto().getId());
        telefone = cliente.getTelefoneResidencial();
        celular = cliente.getTelefoneCelular();

    }

    @SuppressWarnings("unchecked")
    public void iniciarPagamento() throws PagSeguroServiceException {
        pedido = new Pedido();
        podeAlterarPlano = Boolean.FALSE;
        if (!isPostBack()) {
            iniciarClienteEditar(getUsuarioLogado().getIdCliente());
            listaOrigemPagamento = ejb.findAll(OrigemPagamento.class);
            listaProdutosPlanoAssinatura = ejb.listarProdutosKit(cliente.getPlanoAssinatura().getProduto().getId());
            pedido.setOrigemPagamento(listaOrigemPagamento.get(1));
            podeEfetuarPagamento();
            if (podeEfetuarPagamento) {
                pedido.setCliente(cliente);
                pedido.setDataPedido(new Date());
                pedido.setOrigemPagamento(new OrigemPagamento(OrigemPagamento.PAG_SEGURO));
                pedido.setPedidoSituacao(new PedidoSituacao(PedidoSituacao.ABERTO));
                pedido.setPedidoTipo(new PedidoTipo(PedidoTipo.ASSINATURA));
                pedido.setValorFrete(BigDecimal.ZERO);
                pedido.setValorTotalBruto(cliente.getPlanoAssinatura().getValorAdesao());
                pedido.setValorTotalLiquido(cliente.getPlanoAssinatura().getValorAdesao());

                pedido.addPedidoProduto(getPedidoProduto());

                ejb.salvarPedido(pedido);

                checkoutCode = new CreateCheckout().getCheckoutCode(pedido);
            }
        }
    }

    public void podeEfetuarPagamento() {
        if (!isPostBack()) {
            podeEfetuarPagamento = Boolean.TRUE;
            LogPedidoSituacao log = ejb.obterLogPedidoSituacao(cliente.getId());
            if (log != null && log.getStatusPagamento().getId() == StatusPagamento.PAGO) {
                MensagemUtil.addMensagemSucesso("msg.sucesso.pagamento.ja.efetivado");
                podeEfetuarPagamento = Boolean.FALSE;
            } else if (log != null && (log.getStatusPagamento().getId() == StatusPagamento.AGUARDANDO_PAGAMENTO || log.getStatusPagamento().getId() == StatusPagamento.EM_ANALISE)) {
                MensagemUtil.addMensagemSucesso("msg.sucesso.pagamento.em.processamento");
                podeEfetuarPagamento = Boolean.FALSE;
            }
        }
    }

    public String tratarSucessoPagamento() {
        // ejb.salvarLogPedidoSituacao(getLogPedidoSituacao());
        MensagemUtil.addMensagemSucesso("msg.sucesso.pagamento");
        return "/layout/home";
    }

    public String abortarPagamento() throws IOException {
        ejb.removerPedido(pedido);
        return "/layout/home";
    }

    private PedidoProduto getPedidoProduto() {
        PedidoProduto pp = new PedidoProduto();
        pp.setPedidoProdutoSituacao(new PedidoProdutoSituacao(PedidoProdutoSituacao.ABERTO));
        pp.setProduto(cliente.getPlanoAssinatura().getProduto());
        pp.setProdutoValor(ejb.obterValorProduto(cliente.getPlanoAssinatura().getProduto().getId()));
        pp.setQtdProduto(1l);
        pp.setValorDesconto(BigDecimal.ZERO);
        return pp;
    }

    private String checkoutCode;

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
        // TODO: rafael - Ao utilizar este return nao aparece o p:growl de sucesso e erro.
        return "lista_cliente?faces-redirect=true";
    }

    private void setEnderecoPessoa() {
        endereco = new PessoaEndereco(new TipoEndereco(TipoEndereco.RESIDENCIAL), cliente);
        cliente.addPessoaEndereco(endereco);
    }

    private void setTelefonePessoa() {
        telefone = new PessoaTelefone(new TipoTelefone(TipoTelefone.RESIDENCIAL), cliente);
        celular = new PessoaTelefone(new TipoTelefone(TipoTelefone.CELULAR), cliente);
        cliente.addPessoaTelefone(telefone);
        cliente.addPessoaTelefone(celular);
    }

    private void setPessoaConta() {
        pessoaConta = new PessoaConta(cliente);
        pessoaConta.setBanco(new Banco());
        pessoaConta.setTipoConta(new TipoConta());
        pessoaConta.setBolContaPrincipal(Boolean.TRUE);
        cliente.addPessoaConta(pessoaConta);
    }

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
            cliente.setNumCpfCnpj(CpfCnpjUtil.getCpfCnpjLimpo(cliente.getNumCpfCnpj()));
            cliente.getListaEndereco().get(0).setNumCep(cliente.getListaEndereco().get(0).getNumCep().replace("-", ""));

            setUsuario();

            ejb.save(cliente);
        } catch (Exception ex) {
            enviarEmail = Boolean.FALSE;
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

    /**
     * Metodo responsavel por buscar o cep no webService
     * 
     * @param e
     * @return String
     * 
     */
    public String buscarCep(ValueChangeEvent e) {
        String cep = e.getNewValue().toString();
        if (cep != null && !cep.isEmpty() && cep.trim().replaceAll("_", "").replace("-", "").length() == 8) {
            CepService cepService = new CepService();
            CepServiceVO cepServiceVO = null;

            cepServiceVO = buscarCEPWebService(cep, cepService, cepServiceVO);
            if (cepServiceVO != null) {
                if (cepServiceVO.getErro() != null && !cepServiceVO.getErro().isEmpty()) {
                    MensagemUtil.addMensagemInfo("webservice.cep.nao.encontrado");
                    limpaEndereco(cep);
                }
                populaEndereco(cep, cepServiceVO);
            }
        }
        return "lista_cliente?faces-redirect=true";
    }

    private CepServiceVO buscarCEPWebService(String cep, CepService cepService, CepServiceVO cepServiceVO) {
        try {
            cepServiceVO = cepService.buscarCepWebService(cep);
        } catch (CEPProxyException e) {
            MensagemUtil.addMensagemInfo("webservice.cep.erro");
            limpaEndereco(cep);
        }
        return cepServiceVO;
    }

    private void limpaEndereco(String cep) {
        this.endereco.setDescBairro("");
        this.endereco.setDescCidade("");
        this.endereco.setDescEndereco("");
        this.endereco.setNumCep(cep);
        this.endereco.setUf(new UF());
    }

    /**
     * Metodo responsavel por popular os enderecos trago pelo web service
     * 
     * @param cep
     * @param cepServiceVO void
     * 
     */
    private void populaEndereco(String cep, CepServiceVO cepServiceVO) {
        this.endereco.setDescBairro(cepServiceVO.getBairro());
        this.endereco.setDescCidade(cepServiceVO.getLocalidade());
        this.endereco.setDescEndereco(cepServiceVO.getLogradouro());
        this.endereco.setNumCep(cep);
        this.endereco.setUf(getUF(cepServiceVO.getUf()));
    }

    private UF getUF(String codUf) {
        for (UF uf : listaUfs) {
            if (codUf.equals(uf.getCodUf())) {
                return uf;
            }
        }
        return null;
    }

    public void iniciarListarClientes() {
        if (!isPostBack()) {
            listaClientes = ejb.listarClientesSimples();
        }
    }

    private void initListaClienteIndicador() {
        listaClientesIndicadores = ejb.listarClientesIndicadores();
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
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

    public void atualizaListaProdutosPlanoAssinatura() {
        if (cliente.getPlanoAssinatura() != null) {
            listaProdutosPlanoAssinatura = ejb.listarProdutosKit(cliente.getPlanoAssinatura().getProduto().getId());
        }
    }

    public List<Produto> getListaProdutosPlanoAssinatura() {
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

    public PessoaTelefone getCelular() {
        return celular;
    }

    public PessoaConta getPessoaConta() {
        return pessoaConta;
    }

    public List<Cliente> getListaClientesIndicadores() {
        return listaClientesIndicadores;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<OrigemPagamento> getListaOrigemPagamento() {
        return listaOrigemPagamento;
    }

    public String getCheckoutCode() {
        return checkoutCode;
    }

    public void setCheckoutCode(String checkoutCode) {
        this.checkoutCode = checkoutCode;
    }

    public Boolean getPodeEfetuarPagamento() {
        return podeEfetuarPagamento;
    }

    public Boolean getPodeAlterarPlano() {
        return podeAlterarPlano;
    }
}
