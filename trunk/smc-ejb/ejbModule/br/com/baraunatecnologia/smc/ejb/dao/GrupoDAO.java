package br.com.baraunatecnologia.smc.ejb.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.baraunatecnologia.smc.ejb.entity.Grupo;

public class GrupoDAO extends GenericDAO<Grupo> {

	public GrupoDAO(EntityManager em) {
		super(em);
	}

	@Override
	public Class<Grupo> getEntityClass() {
		// TODO Auto-generated method stub
		return Grupo.class;
	}

	public Grupo buscar(String nome) {
		Query query = super.getEntityManager().createQuery(
				"from Grupo g where UPPER(g.nome) LIKE :nome");

		query.setParameter("nome", nome.toUpperCase());

		Grupo grupo = null;
		try {
			grupo = (Grupo) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

		return grupo;
	}

}
