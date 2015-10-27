package br.com.smc.ejb.interfaces;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import br.com.smc.ejb.entity.Incidente;

@Local
public interface IIncidenteLocal extends ICRUDBean<Incidente>{

	public List<Incidente> listarIncidenteIntervalo();
	public List<Incidente> listarIncidenteIntervalo(Date inicio, Date fim);
	public Integer quantidadeIncidenteIntervalo(Date inicio, Date fim);

}