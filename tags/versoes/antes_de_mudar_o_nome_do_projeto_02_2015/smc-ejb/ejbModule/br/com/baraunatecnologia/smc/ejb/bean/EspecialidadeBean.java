package br.com.baraunatecnologia.smc.ejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.baraunatecnologia.smc.ejb.dao.EspecialidadeDAO;
import br.com.baraunatecnologia.smc.ejb.entity.Especialidade;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.IEspecialidadeLocal;
import br.com.baraunatecnologia.smc.ejb.interfaces.IEspecialidadeRemote;

/**
 * Session Bean implementation class CadastrarEspecialidadeBean
 */
@Stateless
public class EspecialidadeBean implements IEspecialidadeRemote, IEspecialidadeLocal {

	@PersistenceContext(unitName = "SMC_UNIT")
	private EntityManager em;
	

	@Override
	public Especialidade inserirEditar(Especialidade especialidade) throws NegocioException {
		return new EspecialidadeDAO(em).inserirEditar(especialidade);
	}

	@Override
	public void deletar(Especialidade especialidade) throws NegocioException {	
		new EspecialidadeDAO(em).deletar(especialidade);
	}

	@Override
	public Especialidade buscar(Integer id) {
		return new EspecialidadeDAO(em).buscar(id);
	}

	@Override
	public List<Especialidade> listar() {
		return new EspecialidadeDAO(em).listar();
	}

	
}
