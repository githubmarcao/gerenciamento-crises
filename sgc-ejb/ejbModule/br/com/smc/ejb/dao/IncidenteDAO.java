package br.com.smc.ejb.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.smc.ejb.entity.Incidente;

public class IncidenteDAO extends GenericDAO<Incidente> {

	public IncidenteDAO(EntityManager em) {
		super(em);
	}

	@Override
	public Class<Incidente> getEntityClass() {
		return Incidente.class;
	}

	public List<Incidente> listarIncidenteIntervalo() {
		return listarIncidenteIntervalo(null, null);
	}

	@SuppressWarnings("unchecked")
	public List<Incidente> listarIncidenteIntervalo(Date inicio, Date fim) {
		StringBuffer sb = new StringBuffer();
		sb.append("from Incidente ");

		if (inicio != null && fim != null) {
			sb.append("where horario BETWEEN :inicio AND :fim ");
		} else {
			sb.append("where horario in (");
			sb.append("select max(horario) from Incidente ");
			sb.append("group by latitude, longitude");
			sb.append(") ");
		}
		
		sb.append("order by horario desc ");

		Query query = super.getEntityManager().createQuery(sb.toString());

		if (inicio != null && fim != null) {
			query.setParameter("inicio", inicio);
			query.setParameter("fim", fim);
		}

		List<Incidente> retorno = new ArrayList<Incidente>();
		try {
			retorno = query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}

		return retorno;
	}

	public Integer quantidadeIncidenteIntervalo(Date inicio, Date fim) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(1) from Incidente as i ");

		if (inicio != null && fim != null) {
			sb.append("where horario BETWEEN :inicio AND :fim ");
		}

		Query query = super.getEntityManager().createQuery(sb.toString());

		if (inicio != null && fim != null) {
			query.setParameter("inicio", inicio);
			query.setParameter("fim", fim);
		}

		Integer retorno = 0;
		try {
			retorno = (Integer) query.getSingleResult();
		} catch (NoResultException ex) {
			return 0;
		}

		return retorno;
	}

}
