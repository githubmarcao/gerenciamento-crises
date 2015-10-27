package br.com.web.jsf.conversor;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.smc.ejb.entity.GrupoUsuario;
import br.com.smc.ejb.interfaces.IGrupoUsuarioLocal;


public class GrupoUsuarioConverter implements Converter {

	@EJB
	private IGrupoUsuarioLocal grupoLocal;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || value.trim() == "") {
			return null;
		}
		GrupoUsuario grupo = grupoLocal.buscar(value);
		return (Object) grupo;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		try {
			return ((GrupoUsuario) value).getNome();
		} catch (Exception e) { }
		return "";
	}

}