package br.com.smc.ejb.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.smc.ejb.dao.IncidenteDAO;
import br.com.smc.ejb.entity.Incidente;
import br.com.smc.ejb.exception.NegocioException;
import br.com.smc.ejb.interfaces.IIncidenteLocal;
import br.com.smc.ejb.interfaces.IIncidenteRemote;

/**
 * Session Bean implementation class CadastrarIncidenteBean
 */
@Stateless
public class IncidenteBean implements IIncidenteRemote, IIncidenteLocal {

	@PersistenceContext(unitName = "SGC_UNIT")
	private EntityManager em;
	

	@Override
	public Incidente inserirEditar(Incidente incidente) throws NegocioException {
		return new IncidenteDAO(em).inserirEditar(incidente);
	}

	@Override
	public void deletar(Incidente incidente) throws NegocioException {	
		new IncidenteDAO(em).deletar(incidente);
	}

	@Override
	public Incidente buscar(Integer id) {
		return new IncidenteDAO(em).buscar(id);
	}

	@Override
	public List<Incidente> listar() {
		return new IncidenteDAO(em).listar();
	}

	public List<Incidente> listarIncidenteIntervalo() {
		return new IncidenteDAO(em).listarIncidenteIntervalo();
	}

	public List<Incidente> listarIncidenteIntervalo(Date inicio, Date fim) {
		return new IncidenteDAO(em).listarIncidenteIntervalo(inicio, fim);
	}

	public Integer quantidadeIncidenteIntervalo(Date inicio, Date fim) {
		return new IncidenteDAO(em).quantidadeIncidenteIntervalo(inicio, fim);
	}
}
