package br.com.baraunatecnologia.smc.ejb.dao;

import javax.persistence.EntityManager;

import br.com.baraunatecnologia.smc.ejb.entity.Paciente;

public class PacienteDAO extends GenericDAO<Paciente> {

	public PacienteDAO(EntityManager em) {
		super(em);
	}

	@Override
	public Class<Paciente> getEntityClass() {
		// TODO Auto-generated method stub
		return Paciente.class;
	}

}
