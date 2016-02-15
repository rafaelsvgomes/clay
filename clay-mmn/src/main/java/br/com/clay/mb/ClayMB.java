package br.com.clay.mb;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.clay.vo.UsuarioLogado;

abstract class ClayMB implements Serializable {

    private static final long serialVersionUID = -1581907663496766815L;

    public UsuarioLogado getUsuarioLogado() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        return (UsuarioLogado) session.getAttribute("usuarioLogado");
    }

}
