package br.com.baraunatecnologia.smc.ejb.interfaces;
import java.util.List;

import javax.ejb.Remote;

import br.com.baraunatecnologia.smc.ejb.entity.Localizacao;

@Remote
public interface ILocalizacaoRemote extends ICRUDBean<Localizacao> {

	public List<Localizacao> listarPorUsuario(Integer idUsuario);
	public List<Localizacao> listarUltimaLocalizacaoUsuarios();

}
