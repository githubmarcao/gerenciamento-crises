package br.com.baraunatecnologia.smc.ejb.interfaces;
import javax.ejb.Remote;

import br.com.baraunatecnologia.smc.ejb.entity.Grupo;

@Remote
public interface IGrupoRemote extends ICRUDBean<Grupo> {

	public Grupo buscar(String nome);

}
