package br.com.baraunatecnologia.smc.ejb.interfaces;
import javax.ejb.Remote;

import br.com.baraunatecnologia.smc.ejb.entity.Consulta;

@Remote
public interface IConsultaRemote extends ICRUDBean<Consulta> {

	
}
