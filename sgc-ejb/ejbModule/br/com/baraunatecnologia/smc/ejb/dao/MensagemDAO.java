package br.com.baraunatecnologia.smc.ejb.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.baraunatecnologia.smc.ejb.entity.Mensagem;

public class MensagemDAO extends GenericDAO<Mensagem> {

	public MensagemDAO(EntityManager em) {
		super(em);
	}

	@Override
	public Class<Mensagem> getEntityClass() {
		return Mensagem.class;
	}
	
	@Override
	public List<Mensagem> listar() {
		// TODO COLOCAR MEU CODIGO AQUI.....
		return super.listar();
	}
}
