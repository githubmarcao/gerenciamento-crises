package br.com.baraunatecnologia.smc.ejb.interfaces;
import javax.ejb.Remote;

import br.com.baraunatecnologia.smc.ejb.entity.GrupoUsuario;

@Remote
public interface IGrupoUsuarioRemote extends ICRUDBean<GrupoUsuario> {

	public GrupoUsuario buscar(String nome);

}
