/**
 * Projeto:         Clay Cosméticos
 * Camada Projeto:  clay-mmn
 * Pacote:          br.com.clay.mb
 * Arquivo:         UsuarioMB.java
 * Data Criação:    22/02/2016
 */
package br.com.clay.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * UsuarioMB
 * 
 * @author Rafael.Silva
 */
@ManagedBean(name = "usuarioMB")
@ViewScoped
public class UsuarioMB extends ClayMB {

    private static final long serialVersionUID = 1L;
    private String senha;
    private String reSenha;

    @PostConstruct
    private void init() {

    }

    public void alterarSenha() {
        System.out.println(senha + " " + reSenha);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getReSenha() {
        return reSenha;
    }

    public void setReSenha(String reSenha) {
        this.reSenha = reSenha;
    }

}
