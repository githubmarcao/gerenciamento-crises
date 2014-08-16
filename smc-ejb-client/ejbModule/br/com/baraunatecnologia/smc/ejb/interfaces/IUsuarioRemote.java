package br.com.baraunatecnologia.smc.ejb.interfaces;
import javax.ejb.Remote;

import br.com.baraunatecnologia.smc.ejb.entity.Medico;
import br.com.baraunatecnologia.smc.ejb.entity.Paciente;
import br.com.baraunatecnologia.smc.ejb.entity.Usuario;

@Remote
public interface IUsuarioRemote extends ICRUDBean<Usuario> {

	public Usuario autenticar(Usuario user);	
}
