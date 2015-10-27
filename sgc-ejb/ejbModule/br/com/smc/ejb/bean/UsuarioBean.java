package br.com.smc.ejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.smc.ejb.dao.UsuarioDAO;
import br.com.smc.ejb.entity.Usuario;
import br.com.smc.ejb.exception.NegocioException;
import br.com.smc.ejb.interfaces.IUsuarioLocal;
import br.com.smc.ejb.interfaces.IUsuarioRemote;

/**
 * Session Bean implementation class CadastrarUsuarioBean
 */
@Stateless
public class UsuarioBean implements IUsuarioRemote, IUsuarioLocal {

	@PersistenceContext(unitName = "SGC_UNIT")
	private EntityManager em;


	@Override
	public Usuario inserirEditar(Usuario usuario) throws NegocioException {
		// Apenas no insert
		if (usuario.getId() == null || usuario.getId() <= 0) {
			validarExisteLogin(usuario.getLogin());
		}
		return new UsuarioDAO(em).inserirEditar(usuario);
	}

	private void validarExisteLogin(String login) throws NegocioException {
		if ((new UsuarioDAO(em).existeLogin(login)) == true) {
			throw new NegocioException("Já existe esse Login.");
		}
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

	@Override
	public Usuario autenticar(Usuario user) {
		return new UsuarioDAO(em).autenticar(user);
	}

}
