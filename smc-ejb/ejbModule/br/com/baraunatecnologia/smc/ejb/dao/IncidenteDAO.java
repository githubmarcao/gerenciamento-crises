package br.com.baraunatecnologia.smc.ejb.dao;

import java.util.ArrayList;
import java.util.Date;
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
	public List<Incidente> listarIncidenteIntervalo(Date inicio, Date fim) {
		Query query = super.getEntityManager().createQuery(
				"from Incidente where horario in ("
				+ "select max(horario) from Incidente ic "
				+ "group by ic.latitude, ic.longitude"
				+ ") order by horario desc ");

		List<Incidente> retorno = new ArrayList<Incidente>();
		try {
			retorno = query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}

		return retorno;
	}

}
