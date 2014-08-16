package br.com.baraunatecnologia.smc.ejb.interfaces;
import javax.ejb.Remote;

import br.com.baraunatecnologia.smc.ejb.entity.Especialidade;

@Remote
public interface IEspecialidadeRemote extends ICRUDBean<Especialidade> {

	
}
