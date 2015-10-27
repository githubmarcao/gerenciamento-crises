package br.com.smc.ejb.interfaces;
import javax.ejb.Remote;

import br.com.smc.ejb.entity.GrupoUsuario;

@Remote
public interface IGrupoUsuarioRemote extends ICRUDBean<GrupoUsuario> {

	public GrupoUsuario buscar(String nome);

}
