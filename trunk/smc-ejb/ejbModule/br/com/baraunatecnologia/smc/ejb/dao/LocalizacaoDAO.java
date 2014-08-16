package br.com.baraunatecnologia.smc.ejb.dao;

import javax.persistence.EntityManager;

import br.com.baraunatecnologia.smc.ejb.entity.Localizacao;

public class LocalizacaoDAO extends GenericDAO<Localizacao> {

	public LocalizacaoDAO(EntityManager em) {
		super(em);
	}

	@Override
	public Class<Localizacao> getEntityClass() {
		// TODO Auto-generated method stub
		return Localizacao.class;
	}

}
