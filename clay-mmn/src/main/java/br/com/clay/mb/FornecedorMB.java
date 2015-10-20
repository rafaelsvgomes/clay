/**
 * Projeto:         Clay Cosméticos
 * Camada Projeto:  clay-mmn
 * Pacote:          br.com.clay.mb
 * Arquivo:         FornecedorMB.java
 * Data Criação:    10 de out de 2015
 */
package br.com.clay.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.clay.entidade.Banco;
import br.com.clay.entidade.Endereco;
import br.com.clay.entidade.Fornecedor;
import br.com.clay.entidade.Pessoa;
import br.com.clay.entidade.PessoaConta;
import br.com.clay.entidade.Produto;
import br.com.clay.entidade.Telefone;
import br.com.clay.entidade.TipoConta;
import br.com.clay.entidade.TipoEndereco;
import br.com.clay.entidade.TipoTelefone;
import br.com.clay.entidade.UF;
import br.com.clay.enums.TipoPessoa;
import br.com.clay.servico.FornecedorServicoEJB;
import br.com.clay.util.MensagemUtil;

/**
 * FornecedorMB é responsável por
 * 
 * @author Felipe
 */
@ManagedBean(name = "fornecedorMB")
@SessionScoped
public class FornecedorMB extends ClayMB {
    private static final long serialVersionUID = -3619457549690706465L;

    private static final String LISTA_FORNECEDOR = "lista_fornecedor";

    @EJB
    FornecedorServicoEJB ejb;

    private Long idSelecionado;
    private Fornecedor fornecedor;
    private List<Fornecedor> fornecedores;
    private Endereco endereco;
    private Telefone telefone;
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
        endereco = new Endereco();
        endereco.setPessoa(fornecedor);

        endereco.setTipoEndereco(new TipoEndereco(TipoEndereco.COMERCIAL));
        fornecedor.setListaEndereco(new ArrayList<Endereco>());
        fornecedor.getListaEndereco().add(endereco);
    }

    private void setTelefonePessoa() {
        telefone = new Telefone();
        telefone.setPessoa(fornecedor);

        telefone.setTipoTelefone(new TipoTelefone(TipoTelefone.COMERCIAL));

        fornecedor.setListaTelefone(new ArrayList<Telefone>());
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
            MensagemUtil.addMensagem("msg.erro.salvar.fornecedor", ex.getMessage());
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
            MensagemUtil.addMensagem("msg.erro.remover.fornecedor", ex.getMessage());
            return "";
        }
        return LISTA_FORNECEDOR;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public PessoaConta getPessoaConta() {
        return pessoaConta;
    }

}
