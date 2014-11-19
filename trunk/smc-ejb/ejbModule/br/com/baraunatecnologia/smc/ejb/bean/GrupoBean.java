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
		preencherCamposObrigatorios(grupo);
		return new GrupoDAO(em).inserirEditar(grupo);
	}

	private void preencherCamposObrigatorios(Grupo grupo) {
		if (grupo != null) {
			if (grupo.getId() == null || grupo.getId() == 0) {
				grupo.setId(null);
			}
			if (grupo.getIdVisualizacao() == null || grupo.getIdVisualizacao() == 0) {
				grupo.setIdVisualizacao(1);
			}
			if (grupo.getIcone() != null && grupo.getIcone().trim() == "") {
				grupo.setIcone(Grupo.ICONE_PADRAO);
			}
		}
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

	@Override
	public Grupo buscar(String nome) {
		return new GrupoDAO(em).buscar(nome);
	}
}
