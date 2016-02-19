package br.com.clay.entidade.validation;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.clay.servico.ClienteServicoEJB;
import br.com.clay.util.CpfCnpjUtil;
import br.com.clay.util.MensagemUtil;

@ManagedBean(name = "cpfCnpjUtilizadoValidator")
@RequestScoped
public class CpfCnpjUtilizadoValidator implements Validator {

    @EJB
    private ClienteServicoEJB ejb;

    public void validate(FacesContext context, UIComponent component, Object submittedValue) throws ValidatorException {
        if (submittedValue == null) {
            return;
        }
        String numCpfCnpj = CpfCnpjUtil.getCpfCnpjLimpo((String) submittedValue);

        if (ejb.cpfCnpjJaUtilizado(numCpfCnpj)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, MensagemUtil.getMessageFromValidationMessages("cpfcnpj.ja.cadastrado"), null));
        }

    }
}
