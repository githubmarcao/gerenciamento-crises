package br.com.baraunatecnologia.smc.ejb.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class GenericDAO <T extends Serializable>{
	
	private EntityManager em;
		
    public GenericDAO(EntityManager em) {    	
    	this.em = em; 
    }

    public abstract Class<T> getEntityClass();

    public T inserirEditar(T entidade) {

        return em.merge(entidade);

    }

    public void deletar(T entidade)  {
    	
    	em.remove(em.merge(entidade));

    }

    public T buscar(Integer id)  {

        return em.find(getEntityClass(), id);

    }

    public List<T> listar() {

        Query dynaQuery = em.createQuery("FROM " + getEntityClass().getName());
        
        return dynaQuery.getResultList();
    }

	public EntityManager getEntityManager() {
		return em;
	}	

}
