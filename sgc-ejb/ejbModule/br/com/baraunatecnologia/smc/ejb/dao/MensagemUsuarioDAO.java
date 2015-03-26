package br.com.baraunatecnologia.smc.ejb.dao;

import javax.persistence.EntityManager;

import br.com.baraunatecnologia.smc.ejb.entity.MensagemUsuario;

public class MensagemUsuarioDAO extends GenericDAO<MensagemUsuario> {

	public MensagemUsuarioDAO(EntityManager em) {
		super(em);
	}

	@Override
	public Class<MensagemUsuario> getEntityClass() {
		return MensagemUsuario.class;
	}
}
