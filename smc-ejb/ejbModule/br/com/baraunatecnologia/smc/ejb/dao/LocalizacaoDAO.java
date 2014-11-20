package br.com.baraunatecnologia.smc.ejb.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.baraunatecnologia.smc.ejb.entity.Grupo;
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
		sb.append("SELECT l from Localizacao as l ");

		// Nao mostrar usuarios administradores no mapa
		sb.append("JOIN l.usuario as u ");
		sb.append("WHERE u.id = l.usuario.id ");
		sb.append("AND u.grupo.id <> " + Grupo.ID_ADMINISTRADOR + " ");
		// FIM - Nao mostrar usuarios administradores no mapa

		if (inicio != null && fim != null) {
			sb.append("AND l.horario in (");
				sb.append("select max(loc.horario) from Localizacao as loc ");
				sb.append("where loc.horario BETWEEN :inicio AND :fim ");
				sb.append("group by loc.usuario.id ");
			sb.append(") ");
		} else {
			sb.append("AND l.horario in (");
			sb.append("select max(loc.horario) from Localizacao as loc ");
			sb.append("group by loc.usuario.id");
			sb.append(") ");
		}

		sb.append("order by l.usuario.id");

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
