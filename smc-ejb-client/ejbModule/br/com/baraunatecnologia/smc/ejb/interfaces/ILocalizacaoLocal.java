package br.com.baraunatecnologia.smc.ejb.interfaces;
import javax.ejb.Local;

import br.com.baraunatecnologia.smc.ejb.entity.Localizacao;

@Local
public interface ILocalizacaoLocal extends ICRUDBean<Localizacao>{

//	public Localizacao autenticar(Localizacao user);
}
