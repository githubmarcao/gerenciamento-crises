package br.com.smc.ejb.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class NegocioException extends Exception{
	
	
	private static final long serialVersionUID = 1L;

	public NegocioException(String pMensagem) {
		super(pMensagem);
	}
	
}
