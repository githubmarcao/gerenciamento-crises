package br.com.baraunatecnologia.smc.ejb.interfaces;
import javax.ejb.Remote;

import br.com.baraunatecnologia.smc.ejb.entity.Medico;

@Remote
public interface IMedicoRemote extends ICRUDBean<Medico> {

	
}
