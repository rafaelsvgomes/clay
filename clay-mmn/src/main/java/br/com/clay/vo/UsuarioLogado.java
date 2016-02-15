package br.com.clay.vo;

import java.io.Serializable;

public class UsuarioLogado implements Serializable {
    private static final long serialVersionUID = 1L;

    public UsuarioLogado() {
    }

    public UsuarioLogado(Long idCliente, String descUsuario, Long idClienteSituacao, String codGrupo) {
        this.idCliente = idCliente;
        this.descUsuario = descUsuario;
        this.idClienteSituacao = idClienteSituacao;
        this.codGrupo = codGrupo;
    }

    private Long idCliente;
    private String descUsuario;
    private Long idClienteSituacao;
    private String codGrupo;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getDescUsuario() {
        return descUsuario;
    }

    public void setDescUsuario(String descUsuario) {
        this.descUsuario = descUsuario;
    }

    public Long getIdClienteSituacao() {
        return idClienteSituacao;
    }

    public void setIdClienteSituacao(Long idClienteSituacao) {
        this.idClienteSituacao = idClienteSituacao;
    }

    public String getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(String codGrupo) {
        this.codGrupo = codGrupo;
    }

}
