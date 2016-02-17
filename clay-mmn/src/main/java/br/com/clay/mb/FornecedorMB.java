/**
 * Projeto:         Clay Cosmï¿½ticos
 * Camada Projeto:  clay-mmn
 * Pacote:          br.com.clay.mb
 * Arquivo:         FornecedorMB.java
 * Data Criaï¿½ï¿½o:    10 de out de 2015
 */
package br.com.clay.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import br.com.clay.entidade.Banco;
import br.com.clay.entidade.Fornecedor;
import br.com.clay.entidade.Pessoa;
import br.com.clay.entidade.PessoaConta;
import br.com.clay.entidade.PessoaEndereco;
import br.com.clay.entidade.PessoaTelefone;
import br.com.clay.entidade.Produto;
import br.com.clay.entidade.TipoConta;
import br.com.clay.entidade.TipoEndereco;
import br.com.clay.entidade.TipoTelefone;
import br.com.clay.entidade.UF;
import br.com.clay.enums.TipoPessoa;
import br.com.clay.servico.FornecedorServicoEJB;
import br.com.clay.util.MensagemUtil;
import br.com.clay.vo.CepServiceVO;
import br.com.clay.webservices.CepService;

/**
 * FornecedorMB ï¿½ responsï¿½vel por
 * 
 * @author Felipe
 */
@ManagedBean(name = "fornecedorMB")
@ViewScoped
public class FornecedorMB extends ClayMB {
    private static final long serialVersionUID = -3619457549690706465L;

    private static final String LISTA_FORNECEDOR = "lista_fornecedor?faces-redirect=true";

    @EJB
    FornecedorServicoEJB ejb;

    private Long idSelecionado;
    private Fornecedor fornecedor;
    private List<Fornecedor> fornecedores;
    private PessoaEndereco endereco;
    private PessoaTelefone telefone;
    private PessoaConta pessoaConta;
    private List<UF> listaUfs;
    private List<Banco> listaBancos;
    private List<TipoConta> listaTipoConta;
    private List<Produto> listaProduto;

    public FornecedorMB() {
    }

    public void incluir() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            fornecedor = new Fornecedor();
            setTelefonePessoa();
            setEnderecoPessoa();
            setPessoaConta();
        }
    }

    private void setEnderecoPessoa() {
        endereco = new PessoaEndereco();
        endereco.setPessoa(fornecedor);

        endereco.setTipoEndereco(new TipoEndereco(TipoEndereco.COMERCIAL));
        fornecedor.setListaEndereco(new ArrayList<PessoaEndereco>());
        fornecedor.getListaEndereco().add(endereco);
    }

    private void setTelefonePessoa() {
        telefone = new PessoaTelefone();
        telefone.setPessoa(fornecedor);

        telefone.setTipoTelefone(new TipoTelefone(TipoTelefone.COMERCIAL));

        fornecedor.setListaTelefone(new ArrayList<PessoaTelefone>());
        fornecedor.getListaTelefone().add(telefone);
    }

    private void setPessoaConta() {
        pessoaConta = new PessoaConta();
        pessoaConta.setPessoa(fornecedor);
        pessoaConta.setBanco(new Banco());
        pessoaConta.setTipoConta(new TipoConta());
        pessoaConta.setBolContaPrincipal(Boolean.TRUE);
        fornecedor.setListaPessoaConta(new ArrayList<PessoaConta>());
        fornecedor.getListaPessoaConta().add(pessoaConta);
    }

    public void editar() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            if (idSelecionado == null) {
                return;
            }
            fornecedor = ejb.obterFornecedor(idSelecionado);
            endereco = fornecedor.getListaEndereco().get(0);
            pessoaConta = fornecedor.getListaPessoaConta().get(0);
            telefone = fornecedor.getListaTelefone().get(0);
        }
    }

    public String salvar() {
        try {
            fornecedor.setDataCadastro(new Date());

            // TODO: rafael - Substituir replaces por Validator
            fornecedor.setNumCpfCnpj(fornecedor.getNumCpfCnpj().replace("-", "").replace(".", "").replace("/", ""));
            fornecedor.getListaEndereco().get(0).setNumCep(fornecedor.getListaEndereco().get(0).getNumCep().replace("-", ""));

            fornecedor.setTipoPessoa(TipoPessoa.J);

            ejb.save(fornecedor);
        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemUtil.addMensagemErro("msg.erro.salvar.fornecedor", ex.getMessage());
            return "";
        }

        return LISTA_FORNECEDOR;
    }

    public String remover() {
        try {
            ejb.remove(fornecedor);
            System.out.println("fornecedor removido");
        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemUtil.addMensagemErro("msg.erro.remover.fornecedor", ex.getMessage());
            return "";
        }
        return LISTA_FORNECEDOR;
    }
    
    /**
     * Método responsável por buscar o cep no webService
     * @param e
     * @return String
     * 
     */
    public String buscarCep(ValueChangeEvent e){
        String cep = e.getNewValue().toString();
        if(cep != null && !cep.isEmpty()){
            CepService cepService = new CepService();
            CepServiceVO cepServiceVO = cepService.buscarCepWebService(cep);
            
            if(cepServiceVO != null){
                populaEndereco(cep, cepServiceVO);
            }
            
        }
        return LISTA_FORNECEDOR;
    }

    /**
     * Método responsável por popular os enderecos trago pelo web service
     * @param cep
     * @param cepServiceVO void
     * 
     */
    private void populaEndereco(String cep, CepServiceVO cepServiceVO) {
        this.endereco.setDescBairro(cepServiceVO.getBairro());
        this.endereco.setDescCidade(cepServiceVO.getCidade());
        this.endereco.setDescEndereco(cepServiceVO.getLogradouro());
        this.endereco.setNumCep(cep);
        this.endereco.setUf(new UF(cepServiceVO.getUf()));
    }

    public List<Fornecedor> getFornecedores() {
        fornecedores = ejb.findAll();
        return fornecedores;
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
    public List<Produto> getListaProduto() {
        if (listaProduto == null) {
            listaProduto = ejb.findAll(Produto.class);
        }
        return listaProduto;
    }

    public Long getIdSelecionado() {
        return idSelecionado;
    }

    public void setIdSelecionado(Long idSelecionado) {
        this.idSelecionado = idSelecionado;
    }

    public Pessoa getFornecedor() {
        return fornecedor;
    }

    public PessoaEndereco getEndereco() {
        return endereco;
    }

    public PessoaTelefone getTelefone() {
        return telefone;
    }

    public PessoaConta getPessoaConta() {
        return pessoaConta;
    }

}
