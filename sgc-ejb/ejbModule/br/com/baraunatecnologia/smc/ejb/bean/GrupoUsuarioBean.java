package br.com.baraunatecnologia.smc.ejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.baraunatecnologia.smc.ejb.dao.GrupoUsuarioDAO;
import br.com.baraunatecnologia.smc.ejb.entity.GrupoUsuario;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.IGrupoUsuarioLocal;
import br.com.baraunatecnologia.smc.ejb.interfaces.IGrupoUsuarioRemote;

/**
 * Session Bean implementation class CadastrarGrupoUsuarioBean
 */
@Stateless
public class GrupoUsuarioBean implements IGrupoUsuarioRemote, IGrupoUsuarioLocal {

	@PersistenceContext(unitName = "SGC_UNIT")
	private EntityManager em;
	

	@Override
	public GrupoUsuario inserirEditar(GrupoUsuario grupo) throws NegocioException {
		preencherCamposObrigatorios(grupo);
		return new GrupoUsuarioDAO(em).inserirEditar(grupo);
	}

	private void preencherCamposObrigatorios(GrupoUsuario grupo) {
		if (grupo != null) {
			if (grupo.getIdVisualizacao() == null || grupo.getIdVisualizacao() == 0) {
				grupo.setIdVisualizacao(1);
			}
			if (grupo.getIcone() != null && grupo.getIcone().trim() == "") {
				grupo.setIcone(GrupoUsuario.ICONE_PADRAO);
			}
		}
	}

	@Override
	public void deletar(GrupoUsuario grupo) throws NegocioException {	
		new GrupoUsuarioDAO(em).deletar(grupo);
	}

	@Override
	public GrupoUsuario buscar(Integer id) {
		return new GrupoUsuarioDAO(em).buscar(id);
	}

	@Override
	public List<GrupoUsuario> listar() {
		return new GrupoUsuarioDAO(em).listar();
	}

	@Override
	public GrupoUsuario buscar(String nome) {
		return new GrupoUsuarioDAO(em).buscar(nome);
	}
}
