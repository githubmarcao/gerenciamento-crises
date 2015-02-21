package br.com.baraunatecnologia.smc.ejb.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.baraunatecnologia.smc.ejb.entity.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario> {

	public UsuarioDAO(EntityManager em) {
		super(em);
	}

	@Override
	public Class<Usuario> getEntityClass() {
		// TODO Auto-generated method stub
		return Usuario.class;
	}

	public Usuario autenticar(Usuario user) {
		Query query = super.getEntityManager().createQuery(
				"from Usuario u where u.login=:login and u.senha=:senha");

		query.setParameter("login", user.getLogin());
		query.setParameter("senha", user.getSenha());

		Usuario usuario = null;
		try {
			usuario = (Usuario) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

		return usuario;
	}

	public boolean existeLogin(String login) {
		Query query = super.getEntityManager().createQuery(
				"from Usuario u where u.login=:login");

		query.setParameter("login", login);

		Usuario usuario = null;
		try {
			usuario = (Usuario) query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}

		if (usuario == null) {
			return false;
		} else {
			return true;
		}
	}

}
