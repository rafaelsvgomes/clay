package br.com.clay.util;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

public final class MensagemUtil {

    private static final String SUCESSO = "Sucesso";
    private static final String ERRO = "Erro";

    /**
     * Adiciona um mensagem no contexto do Faces (<code>FacesContext</code>).
     * 
     * @param titulo
     * @param detalhe
     */
    private static void addMessage(Severity severity, String titulo, String detalhe) {
        getCurrentInstance().addMessage(null, new FacesMessage(severity, titulo, detalhe));
    }

    /**
     * @param resumo
     * @param detalhe
     */
    public static void addMensagemSucesso(String detalhe) {
        addMessage(FacesMessage.SEVERITY_INFO, SUCESSO, getMessageFromI18N(detalhe));
    }

    public static void addMensagemErro(String titulo, String detalhe) {
        addMessage(FacesMessage.SEVERITY_ERROR, getMessageFromI18N(titulo), detalhe);
    }

    /**
     * @param key
     * @return Recupera a mensagem do arquivo properties <code>ResourceBundle</code>.
     */
    public static String getMessageFromI18N(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages_labels", getCurrentInstance().getViewRoot().getLocale());
        return bundle.getString(key);
    }

    public static String getPropriedades(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("propriedades", getCurrentInstance().getViewRoot().getLocale());
        return bundle.getString(key);
    }
}
