package br.com.clay.mb;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import br.com.clay.util.UsuarioSessaoUtil;
import br.com.clay.vo.UsuarioLogado;

abstract class ClayMB implements Serializable {

    private static final long serialVersionUID = -1581907663496766815L;

    public UsuarioLogado getUsuarioLogado() {
        return new UsuarioSessaoUtil().getUsuarioLogado();
    }

    public Boolean isPostBack() {
        return FacesContext.getCurrentInstance().isPostback();
    }

}
