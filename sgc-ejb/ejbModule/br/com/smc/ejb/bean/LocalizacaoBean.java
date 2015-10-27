package br.com.smc.ejb.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.smc.ejb.dao.LocalizacaoDAO;
import br.com.smc.ejb.entity.Localizacao;
import br.com.smc.ejb.exception.NegocioException;
import br.com.smc.ejb.interfaces.ILocalizacaoLocal;
import br.com.smc.ejb.interfaces.ILocalizacaoRemote;

/**
 * Session Bean implementation class CadastrarLocalizacaoBean
 */
@Stateless
public class LocalizacaoBean implements ILocalizacaoRemote, ILocalizacaoLocal {

	@PersistenceContext(unitName = "SGC_UNIT")
	private EntityManager em;
	

	@Override
	public Localizacao inserirEditar(Localizacao localizacao) throws NegocioException {
		if (localizacao != null && localizacao.getHorario() == null) {
			localizacao.setHorario(new Date());
		}
		return new LocalizacaoDAO(em).inserirEditar(localizacao);
	}

	@Override
	public void deletar(Localizacao localizacao) throws NegocioException {	
		new LocalizacaoDAO(em).deletar(localizacao);
	}

	@Override
	public Localizacao buscar(Integer id) {
		return new LocalizacaoDAO(em).buscar(id);
	}

	@Override
	public List<Localizacao> listar() {
		return new LocalizacaoDAO(em).listar();
	}

	public List<Localizacao> listarPorUsuario(Integer idUsuario) {
		return new LocalizacaoDAO(em).listarPorUsuario(idUsuario);
	}

	public List<Localizacao> listarUltimaLocalizacaoUsuarios() {
		return new LocalizacaoDAO(em).listarUltimaLocalizacaoUsuarios();
	}

	public List<Localizacao> listarUltimaLocalizacaoUsuarios(Date inicio, Date fim) {
		return new LocalizacaoDAO(em).listarUltimaLocalizacaoUsuarios(inicio, fim);
	}
}
