package br.com.baraunatecnologia.smc.ejb.dao;

import javax.persistence.EntityManager;

import br.com.baraunatecnologia.smc.ejb.entity.Medico;

public class MedicoDAO extends GenericDAO<Medico> {

	public MedicoDAO(EntityManager em) {
		super(em);
	}

	@Override
	public Class<Medico> getEntityClass() {
		// TODO Auto-generated method stub
		return Medico.class;
	}

}
