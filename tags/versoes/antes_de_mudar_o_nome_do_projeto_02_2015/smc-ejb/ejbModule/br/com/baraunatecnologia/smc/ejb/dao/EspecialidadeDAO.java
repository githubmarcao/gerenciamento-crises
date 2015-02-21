package br.com.baraunatecnologia.smc.ejb.dao;

import javax.persistence.EntityManager;

import br.com.baraunatecnologia.smc.ejb.entity.Especialidade;

public class EspecialidadeDAO extends GenericDAO<Especialidade> {

	public EspecialidadeDAO(EntityManager em) {
		super(em);
	}

	@Override
	public Class<Especialidade> getEntityClass() {
		// TODO Auto-generated method stub
		return Especialidade.class;
	}

}
