package br.com.baraunatecnologia.smc.ejb.dao;

import javax.persistence.EntityManager;

import br.com.baraunatecnologia.smc.ejb.entity.Mensagem;

public class MensagemDAO extends GenericDAO<Mensagem> {

	public MensagemDAO(EntityManager em) {
		super(em);
	}

	@Override
	public Class<Mensagem> getEntityClass() {
		// TODO Auto-generated method stub
		return Mensagem.class;
	}
}
