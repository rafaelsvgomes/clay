/**
 * Projeto:         Clay Cosméticos
 * Camada Projeto:  clay-mmn
 * Pacote:          br.com.clay.mb
 * Arquivo:         UsuarioMB.java
 * Data Criação:    22/02/2016
 */
package br.com.clay.mb;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.clay.entidade.Usuario;
import br.com.clay.servico.UsuarioServicoEJB;
import br.com.clay.util.MensagemUtil;
import br.com.clay.util.SenhaUtil;

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

    @EJB
    UsuarioServicoEJB ejb;

    public void alterarSenha() {
        try {
            Usuario usuario = ejb.find(getUsuarioLogado().getIdUsuario());
            usuario.setDescSenha(SenhaUtil.criptografarSenha(senha));
            ejb.save(usuario);
            MensagemUtil.addMensagemSucesso("msg.sucesso.alterar.senha");
        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemUtil.addMensagemErro("msg.erro.alterar.senha", ex.getMessage());
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
