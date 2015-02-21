package br.com.baraunatecnologia.smc.ejb.interfaces;
import javax.ejb.Local;

import br.com.baraunatecnologia.smc.ejb.entity.Usuario;

@Local
public interface IUsuarioLocal extends ICRUDBean<Usuario>{

	public Usuario autenticar(Usuario user);
}
