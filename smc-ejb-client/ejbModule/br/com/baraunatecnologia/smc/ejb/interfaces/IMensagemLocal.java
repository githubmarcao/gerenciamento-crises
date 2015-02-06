package br.com.baraunatecnologia.smc.ejb.interfaces;
import javax.ejb.Local;

import br.com.baraunatecnologia.smc.ejb.entity.Mensagem;

@Local
public interface IMensagemLocal extends ICRUDBean<Mensagem>{

}
