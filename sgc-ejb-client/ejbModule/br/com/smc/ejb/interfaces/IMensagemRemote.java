package br.com.smc.ejb.interfaces;
import javax.ejb.Remote;

import br.com.smc.ejb.entity.Mensagem;

@Remote
public interface IMensagemRemote extends ICRUDBean<Mensagem> {

}
