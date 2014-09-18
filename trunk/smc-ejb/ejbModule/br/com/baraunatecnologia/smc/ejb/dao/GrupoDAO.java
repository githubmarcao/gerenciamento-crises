package br.com.baraunatecnologia.smc.ejb.dao;

import javax.persistence.EntityManager;

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

}
