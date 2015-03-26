package br.com.baraunatecnologia.smc.ejb.interfaces;
import javax.ejb.Local;

import br.com.baraunatecnologia.smc.ejb.entity.Mensagem;
import br.com.baraunatecnologia.smc.ejb.entity.MensagemGrupoUsuario;
import br.com.baraunatecnologia.smc.ejb.entity.MensagemUsuario;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;

@Local
public interface IMensagemLocal extends ICRUDBean<Mensagem>{

	public Mensagem inserirEditar(Mensagem mensagem, MensagemUsuario mensagemUsuario, MensagemGrupoUsuario mensagemGrupoUsuario) throws NegocioException;

}
