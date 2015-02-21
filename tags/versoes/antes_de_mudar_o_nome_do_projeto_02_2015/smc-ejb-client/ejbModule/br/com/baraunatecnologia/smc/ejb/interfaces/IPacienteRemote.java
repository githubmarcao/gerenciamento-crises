package br.com.baraunatecnologia.smc.ejb.interfaces;
import javax.ejb.Remote;

import br.com.baraunatecnologia.smc.ejb.entity.Paciente;

@Remote
public interface IPacienteRemote extends ICRUDBean<Paciente> {

	
}
