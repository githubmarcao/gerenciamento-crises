package br.com.smc.ejb.interfaces;
import javax.ejb.Local;

import br.com.smc.ejb.entity.GrupoUsuario;

@Local
public interface IGrupoUsuarioLocal extends ICRUDBean<GrupoUsuario>{

	public GrupoUsuario buscar(String nome);

}
