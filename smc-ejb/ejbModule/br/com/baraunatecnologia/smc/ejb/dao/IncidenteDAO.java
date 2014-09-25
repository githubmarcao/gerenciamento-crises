package br.com.baraunatecnologia.smc.ejb.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.baraunatecnologia.smc.ejb.entity.Incidente;

public class IncidenteDAO extends GenericDAO<Incidente> {

	public IncidenteDAO(EntityManager em) {
		super(em);
	}

	@Override
	public Class<Incidente> getEntityClass() {
		// TODO Auto-generated method stub
		return Incidente.class;
	}

	@SuppressWarnings("unchecked")
	public List<Incidente> listarIncidenteIntervalo() {
		Query query = super.getEntityManager().createQuery(
				" SELECT distinct(i.latitude, i.longitude), i.id, i.descricao, i.latitude, i.longitude, i.horario"
				+ " FROM Incidente i"
				+ " Order by i.horario desc, i.id, i.descricao, i.latitude, i.longitude, (i.latitude, i.longitude)");

		List<Incidente> retorno = new ArrayList<Incidente>();
		try {
			retorno = query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}

		return retorno;
	}

}
