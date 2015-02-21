package br.com.baraunatecnologia.smc.ejb.interfaces;
import javax.ejb.Local;

import br.com.baraunatecnologia.smc.ejb.entity.GrupoUsuario;

@Local
public interface IGrupoUsuarioLocal extends ICRUDBean<GrupoUsuario>{

	public GrupoUsuario buscar(String nome);

}
