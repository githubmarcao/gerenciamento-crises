/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baraunatecnologia.web.jsf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * 
 * @author Marco
 */
@FacesValidator("telefoneValidador")
public class TelefoneValidador implements Validator {

	public TelefoneValidador() {
	}

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		String valorStr = value.toString();
		
		if(valorStr.equals("") || !valorStr.contains("-")){
			FacesMessage msg = new FacesMessage("Insira um telefone valido com formato 9999-9999.",
			"Formato de telefone invalido");
			
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			
			throw new ValidatorException(msg);
		}

	}

}
