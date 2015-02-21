package br.com.baraunatecnologia.smc.ejb.interfaces;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import br.com.baraunatecnologia.smc.ejb.entity.Localizacao;

@Local
public interface ILocalizacaoLocal extends ICRUDBean<Localizacao>{

	public List<Localizacao> listarPorUsuario(Integer idUsuario);
	public List<Localizacao> listarUltimaLocalizacaoUsuarios();
	public List<Localizacao> listarUltimaLocalizacaoUsuarios(Date inicio, Date fim);

}
