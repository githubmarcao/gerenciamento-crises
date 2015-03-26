package br.com.baraunatecnologia.smc.ejb.dao;

import javax.persistence.EntityManager;

import br.com.baraunatecnologia.smc.ejb.entity.MensagemGrupoUsuario;

public class MensagemGrupoUsuarioDAO extends GenericDAO<MensagemGrupoUsuario> {

	public MensagemGrupoUsuarioDAO(EntityManager em) {
		super(em);
	}

	@Override
	public Class<MensagemGrupoUsuario> getEntityClass() {
		return MensagemGrupoUsuario.class;
	}
}
