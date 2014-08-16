package br.com.baraunatecnologia.smc.ejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.baraunatecnologia.smc.ejb.dao.MedicoDAO;
import br.com.baraunatecnologia.smc.ejb.entity.Medico;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.IMedicoLocal;
import br.com.baraunatecnologia.smc.ejb.interfaces.IMedicoRemote;

/**
 * Session Bean implementation class CadastrarMedicoBean
 */
@Stateless
public class MedicoBean implements IMedicoRemote, IMedicoLocal {

	@PersistenceContext(unitName = "SMC_UNIT")
	private EntityManager em;
	

	@Override
	public Medico inserirEditar(Medico medico) throws NegocioException {
		return new MedicoDAO(em).inserirEditar(medico);
	}

	@Override
	public void deletar(Medico medico) throws NegocioException {	
		new MedicoDAO(em).deletar(medico);
	}

	@Override
	public Medico buscar(Integer id) {
		return new MedicoDAO(em).buscar(id);
	}

	@Override
	public List<Medico> listar() {
		return new MedicoDAO(em).listar();
	}

	
}
