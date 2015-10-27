package br.com.smc.ejb.interfaces;
import javax.ejb.Local;

import br.com.smc.ejb.entity.Usuario;

@Local
public interface IUsuarioLocal extends ICRUDBean<Usuario>{

	public Usuario autenticar(Usuario user);
}
