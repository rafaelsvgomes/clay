package br.com.clay.util;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;

public final class MensagemUtil {

    /**
     * @param key
     * @return Recupera a mensagem do arquivo properties <code>ResourceBundle</code>.
     */
    public static String getMessageFromI18N(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages_labels", getCurrentInstance().getViewRoot().getLocale());
        return bundle.getString(key);
    }

    /**
     * Adiciona um mensagem no contexto do Faces (<code>FacesContext</code>).
     * 
     * @param summary
     * @param detail
     */
    private static void addMessage(String summary, String detail) {
        getCurrentInstance().addMessage(null, new FacesMessage(summary, summary.concat("<br/>").concat(detail)));
    }

    /**
     * @param resumo
     * @param detalhe
     */
    public static void addMensagem(String chave, String detalhe) {
        addMessage(getMessageFromI18N(chave), detalhe);
    }

    public static String getPropriedades(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("propriedades", getCurrentInstance().getViewRoot().getLocale());
        return bundle.getString(key);
    }
}
