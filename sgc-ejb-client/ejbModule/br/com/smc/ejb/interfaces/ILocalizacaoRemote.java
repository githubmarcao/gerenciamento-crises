package br.com.smc.ejb.interfaces;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import br.com.smc.ejb.entity.Localizacao;

@Remote
public interface ILocalizacaoRemote extends ICRUDBean<Localizacao> {

	public List<Localizacao> listarPorUsuario(Integer idUsuario);
	public List<Localizacao> listarUltimaLocalizacaoUsuarios();
	public List<Localizacao> listarUltimaLocalizacaoUsuarios(Date inicio, Date fim);

}
