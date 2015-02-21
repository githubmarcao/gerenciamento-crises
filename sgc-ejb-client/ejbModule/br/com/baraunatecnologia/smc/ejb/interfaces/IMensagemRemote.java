package br.com.baraunatecnologia.smc.ejb.interfaces;
import javax.ejb.Remote;

import br.com.baraunatecnologia.smc.ejb.entity.Mensagem;

@Remote
public interface IMensagemRemote extends ICRUDBean<Mensagem> {

}
