package br.com.baraunatecnologia.smc.ejb.dao;

import java.util.ArrayList;
import java.util.Date;
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
				"from Localizacao where id_usuario=:id_usuario "
				+ "order by horario desc");

		query.setParameter("id_usuario", idUsuario);

		List<Localizacao> retorno = new ArrayList<Localizacao>();
		try {
			retorno = query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}

		return retorno;
	}

	public List<Localizacao> listarUltimaLocalizacaoUsuarios() {
		return listarUltimaLocalizacaoUsuarios(null, null);
	}

	@SuppressWarnings("unchecked")
	public List<Localizacao> listarUltimaLocalizacaoUsuarios(Date inicio, Date fim) {
		StringBuffer sb = new StringBuffer();
		sb.append("from Localizacao ");

		if (inicio != null && fim != null) {
			sb.append("where horario in (");
				sb.append("select max(horario) from Localizacao ");
				sb.append("where horario BETWEEN :inicio AND :fim ");
				sb.append("group by id_usuario ");
			sb.append(") ");
		} else {
			sb.append("where horario in (");
			sb.append("select max(horario) from Localizacao ");
			sb.append("group by id_usuario");
			sb.append(") ");
		}

		sb.append("order by id_usuario");

		Query query = super.getEntityManager().createQuery(sb.toString());

		if (inicio != null && fim != null) {
			query.setParameter("inicio", inicio);
			query.setParameter("fim", fim);
		}

		List<Localizacao> retorno = new ArrayList<Localizacao>();
		try {
			retorno = query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}

		return retorno;
	}

}
