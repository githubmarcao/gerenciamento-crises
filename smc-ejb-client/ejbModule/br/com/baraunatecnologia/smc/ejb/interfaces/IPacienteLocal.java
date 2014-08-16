package br.com.baraunatecnologia.smc.ejb.interfaces;
import javax.ejb.Local;

import br.com.baraunatecnologia.smc.ejb.entity.Paciente;

@Local
public interface IPacienteLocal extends ICRUDBean<Paciente>{

	
}
