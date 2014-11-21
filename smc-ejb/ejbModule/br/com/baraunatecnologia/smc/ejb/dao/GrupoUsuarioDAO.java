package br.com.baraunatecnologia.smc.ejb.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.baraunatecnologia.smc.ejb.entity.GrupoUsuario;

public class GrupoUsuarioDAO extends GenericDAO<GrupoUsuario> {

	public GrupoUsuarioDAO(EntityManager em) {
		super(em);
	}

	@Override
	public Class<GrupoUsuario> getEntityClass() {
		// TODO Auto-generated method stub
		return GrupoUsuario.class;
	}

	public GrupoUsuario buscar(String nome) {
		Query query = super.getEntityManager().createQuery(
				"from GrupoUsuario g where UPPER(g.nome) LIKE :nome");

		query.setParameter("nome", nome.toUpperCase());

		GrupoUsuario grupo = null;
		try {
			grupo = (GrupoUsuario) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

		return grupo;
	}

}
