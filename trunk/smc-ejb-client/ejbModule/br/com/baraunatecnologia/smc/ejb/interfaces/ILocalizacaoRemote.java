package br.com.baraunatecnologia.smc.ejb.interfaces;
import javax.ejb.Remote;

import br.com.baraunatecnologia.smc.ejb.entity.Localizacao;

@Remote
public interface ILocalizacaoRemote extends ICRUDBean<Localizacao> {

//	public Localizacao autenticar(Localizacao user);	
}
