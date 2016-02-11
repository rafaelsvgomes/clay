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

@ManagedBean(name = "emailValidator")
@RequestScoped
public class EmailValidator implements Validator {

    @EJB
    private ClienteServicoEJB ejb;

    public void validate(FacesContext context, UIComponent component, Object submittedValue) throws ValidatorException {
        if (submittedValue == null) {
            return; // Let required="true" handle.
        }

        String username = (String) submittedValue;

        if (ejb.emailJaUtilizado(username)) {
            throw new ValidatorException(new FacesMessage("Nome de usuário já utilizado"));
        }
    }
}
