package br.com.baraunatecnologia.smc.ejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.baraunatecnologia.smc.ejb.dao.GrupoDAO;
import br.com.baraunatecnologia.smc.ejb.entity.Grupo;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.IGrupoLocal;
import br.com.baraunatecnologia.smc.ejb.interfaces.IGrupoRemote;

/**
 * Session Bean implementation class CadastrarGrupoBean
 */
@Stateless
public class GrupoBean implements IGrupoRemote, IGrupoLocal {

	@PersistenceContext(unitName = "SMC_UNIT")
	private EntityManager em;
	

	@Override
	public Grupo inserirEditar(Grupo grupo) throws NegocioException {
		return new GrupoDAO(em).inserirEditar(grupo);
	}

	@Override
	public void deletar(Grupo grupo) throws NegocioException {	
		new GrupoDAO(em).deletar(grupo);
	}

	@Override
	public Grupo buscar(Integer id) {
		return new GrupoDAO(em).buscar(id);
	}

	@Override
	public List<Grupo> listar() {
		return new GrupoDAO(em).listar();
	}

	
}
