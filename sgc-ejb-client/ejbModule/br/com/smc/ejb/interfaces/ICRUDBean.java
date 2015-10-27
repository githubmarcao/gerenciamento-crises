package br.com.smc.ejb.interfaces;

import java.io.Serializable;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.smc.ejb.exception.NegocioException;



public interface ICRUDBean <T extends Serializable> {
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	T inserirEditar(T entidade) throws NegocioException;
		
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	void deletar(T entidade) throws NegocioException;
    
    T buscar(Integer id) throws NegocioException;;
            
    List<T> listar() throws NegocioException;
	
}
