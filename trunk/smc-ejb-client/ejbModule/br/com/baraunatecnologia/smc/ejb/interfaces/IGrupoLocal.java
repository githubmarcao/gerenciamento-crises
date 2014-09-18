package br.com.baraunatecnologia.smc.ejb.interfaces;
import javax.ejb.Local;

import br.com.baraunatecnologia.smc.ejb.entity.Grupo;

@Local
public interface IGrupoLocal extends ICRUDBean<Grupo>{

//	public Grupo autenticar(Grupo user);
}
