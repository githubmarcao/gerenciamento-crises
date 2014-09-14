package br.com.baraunatecnologia.smc.ejb.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

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

	@SuppressWarnings("unchecked")
	public List<Localizacao> listarPorUsuario(Integer idUsuario) {
		Query query = super.getEntityManager().createQuery(
				"from Localizacao l where l.id_usuario=:id_usuario");

		query.setParameter("id_usuario", idUsuario);

		List<Localizacao> retorno = new ArrayList<Localizacao>();
		try {
			retorno = query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}

		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<Localizacao> listarUltimaLocalizacaoUsuarios() {
		Query query = super.getEntityManager().createQuery(
				"from Localizacao where horario in ("
					+ "select max(horario) from Localizacao "
					+ "group by id_usuario"
				+ ") order by id_usuario");

		List<Localizacao> retorno = new ArrayList<Localizacao>();
		try {
			retorno = query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}

		return retorno;
	}

}
