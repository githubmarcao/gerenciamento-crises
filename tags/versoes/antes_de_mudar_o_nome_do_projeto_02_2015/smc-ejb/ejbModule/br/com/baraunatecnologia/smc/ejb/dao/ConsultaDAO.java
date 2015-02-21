package br.com.baraunatecnologia.smc.ejb.dao;

import javax.persistence.EntityManager;

import br.com.baraunatecnologia.smc.ejb.entity.Consulta;

public class ConsultaDAO extends GenericDAO<Consulta> {

	public ConsultaDAO(EntityManager em) {
		super(em);
	}

	@Override
	public Class<Consulta> getEntityClass() {
		// TODO Auto-generated method stub
		return Consulta.class;
	}

}
