package br.com.baraunatecnologia.web.jsf.conversor;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.baraunatecnologia.smc.ejb.entity.Grupo;
import br.com.baraunatecnologia.smc.ejb.interfaces.IGrupoLocal;


public class GrupoUsuarioConverter implements Converter {

	@EJB
	private IGrupoLocal grupoLocal;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || value.trim() == "") {
			return null;
		}
		Grupo grupo = grupoLocal.buscar(value);
		return (Object) grupo;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		try {
			return ((Grupo) value).getNome();
		} catch (Exception e) { }
		return "";
	}

}