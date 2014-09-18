package br.com.baraunatecnologia.smc.ejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.baraunatecnologia.smc.ejb.dao.UsuarioDAO;
import br.com.baraunatecnologia.smc.ejb.entity.Usuario;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.IUsuarioLocal;
import br.com.baraunatecnologia.smc.ejb.interfaces.IUsuarioRemote;

/**
 * Session Bean implementation class CadastrarUsuarioBean
 */
@Stateless
public class UsuarioBean implements IUsuarioRemote, IUsuarioLocal {

	@PersistenceContext(unitName = "SMC_UNIT")
	private EntityManager em;
	

	@Override
	public Usuario inserirEditar(Usuario usuario) throws NegocioException {
		
		return new UsuarioDAO(em).inserirEditar(usuario);
	}


	@Override
	public void deletar(Usuario usuario) throws NegocioException {	

		new UsuarioDAO(em).deletar(usuario);
	}

	@Override
	public Usuario buscar(Integer id) {
		return new UsuarioDAO(em).buscar(id); 
	}

	@Override
	public List<Usuario> listar() {
		return new UsuarioDAO(em).listar();
	}
	
	public Usuario autenticar(Usuario user) {
		return new UsuarioDAO(em).autenticar(user);
	}

	
}
