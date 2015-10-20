package br.com.clay.entidade;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.clay.enums.TipoPessoa;
import br.com.clay.enums.TipoSexo;

@Entity
@Table(name = "PESSOA")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "seqpessoa", sequenceName = "seqpessoa", allocationSize = 1)
public abstract class Pessoa extends ClayEntidade {
    private static final long serialVersionUID = -8922414503953244338L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqpessoa")
    @Column(name = "idPessoa", unique = true, nullable = false)
    protected Long id;

    @NotEmpty(message = "{pessoa.nome.vazio}")
    private String nomePessoa;

    @Column(name = "dsRazaoSocial")
    private String descRazaoSocial;

    @Enumerated(EnumType.STRING)
    @Column(name = "codTipoPessoa")
    private TipoPessoa tipoPessoa;

    private String numCpfCnpj;

    @Enumerated(EnumType.STRING)
    @Column(name = "codSexo")
    private TipoSexo tipoSexo;

    @Column(name = "dataNascimento")
    private Date dataNascimento;

    @NotEmpty
    @Pattern(regexp = ".+@.+\\.[a-z]+")
    @Column(name = "dsEmail")
    private String descEmail;

    @Column(name = "dataCadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Endereco> listaEndereco;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Telefone> listaTelefone;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<PessoaConta> listaPessoaConta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getDescRazaoSocial() {
        return descRazaoSocial;
    }

    public void setDescRazaoSocial(String descRazaoSocial) {
        this.descRazaoSocial = descRazaoSocial;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getNumCpfCnpj() {
        return numCpfCnpj;
    }

    public void setNumCpfCnpj(String numCpfCnpj) {
        this.numCpfCnpj = numCpfCnpj;
    }

    public TipoSexo getTipoSexo() {
        return tipoSexo;
    }

    public void setTipoSexo(TipoSexo tipoSexo) {
        this.tipoSexo = tipoSexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<Endereco> getListaEndereco() {
        return listaEndereco;
    }

    public void setListaEndereco(List<Endereco> listaEndereco) {
        this.listaEndereco = listaEndereco;
    }

    public List<Telefone> getListaTelefone() {
        return listaTelefone;
    }

    public void setListaTelefone(List<Telefone> listaTelefone) {
        this.listaTelefone = listaTelefone;
    }

    public String getDescEmail() {
        return descEmail;
    }

    public void setDescEmail(String descEmail) {
        this.descEmail = descEmail;
    }

    public List<PessoaConta> getListaPessoaConta() {
        return listaPessoaConta;
    }

    public void setListaPessoaConta(List<PessoaConta> listaPessoaConta) {
        this.listaPessoaConta = listaPessoaConta;
    }
}
