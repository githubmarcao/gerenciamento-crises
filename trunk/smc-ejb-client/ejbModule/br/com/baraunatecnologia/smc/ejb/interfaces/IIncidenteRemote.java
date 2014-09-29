package br.com.baraunatecnologia.smc.ejb.interfaces;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import br.com.baraunatecnologia.smc.ejb.entity.Incidente;

@Remote
public interface IIncidenteRemote extends ICRUDBean<Incidente> {

	public List<Incidente> listarIncidenteIntervalo();
	public List<Incidente> listarIncidenteIntervalo(Date inicio, Date fim);

}