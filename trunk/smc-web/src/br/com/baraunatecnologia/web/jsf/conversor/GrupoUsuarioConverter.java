package br.com.baraunatecnologia.web.jsf.conversor;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import br.com.baraunatecnologia.smc.ejb.enumerator.GrupoUsuarioClassEnum.GrupoUsuarioEnum;

@FacesConverter("grupoUsuarioConverter") 
public class GrupoUsuarioConverter extends EnumConverter {

	public GrupoUsuarioConverter() {
        super(GrupoUsuarioEnum.class);
    }

}