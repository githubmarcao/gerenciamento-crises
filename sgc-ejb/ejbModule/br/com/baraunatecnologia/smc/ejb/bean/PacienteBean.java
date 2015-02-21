package br.com.baraunatecnologia.smc.ejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.baraunatecnologia.smc.ejb.dao.PacienteDAO;
import br.com.baraunatecnologia.smc.ejb.entity.Paciente;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.IPacienteLocal;
import br.com.baraunatecnologia.smc.ejb.interfaces.IPacienteRemote;

/**
 * Session Bean implementation class CadastrarPacienteBean
 */
@Stateless
public class PacienteBean implements IPacienteRemote, IPacienteLocal {

	@PersistenceContext(unitName = "SMC_UNIT")
	private EntityManager em;
	

	@Override
	public Paciente inserirEditar(Paciente paciente) throws NegocioException {
		return new PacienteDAO(em).inserirEditar(paciente);
	}

	@Override
	public void deletar(Paciente paciente) throws NegocioException {	
		new PacienteDAO(em).deletar(paciente);
	}

	@Override
	public Paciente buscar(Integer id) {
		return new PacienteDAO(em).buscar(id);
	}

	@Override
	public List<Paciente> listar() {
		return new PacienteDAO(em).listar();
	}

	
}
