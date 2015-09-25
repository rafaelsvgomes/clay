package br.com.clay.entidade;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PESSOA", schema = "public")
@SequenceGenerator(name="seqpessoa", sequenceName="seqpessoa", allocationSize = 1)
public class Pessoa implements Serializable {
	private static final long serialVersionUID = -8922414503953244338L;

	@Id
	@Column(name = "idPessoa")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqpessoa")
	private Long id;
	
	private String nomePessoa;
	
	private Integer tipoPessoa;
	
	private String numCpfCnpj;
	
	@Column(name = "dataNascimento")
	private Date dataNascimento;
	
	@Column(name = "dataCadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCadastro;

	public String getNumCpfCnpj() {
		return numCpfCnpj;
	}

	public void setNumCpfCnpj(String numCpfCnpj) {
		this.numCpfCnpj = numCpfCnpj;
	}

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

	public Integer getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(Integer tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
