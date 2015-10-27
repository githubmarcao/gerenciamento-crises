package br.com.smc.ejb.interfaces;
import javax.ejb.Local;

import br.com.smc.ejb.entity.Mensagem;
import br.com.smc.ejb.entity.MensagemGrupoUsuario;
import br.com.smc.ejb.entity.MensagemUsuario;
import br.com.smc.ejb.exception.NegocioException;

@Local
public interface IMensagemLocal extends ICRUDBean<Mensagem>{

	public Mensagem inserirEditar(Mensagem mensagem, MensagemUsuario mensagemUsuario, MensagemGrupoUsuario mensagemGrupoUsuario) throws NegocioException;

}
