package br.com.baraunatecnologia.smc.ejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.baraunatecnologia.smc.ejb.dao.LocalizacaoDAO;
import br.com.baraunatecnologia.smc.ejb.entity.Localizacao;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.ILocalizacaoLocal;
import br.com.baraunatecnologia.smc.ejb.interfaces.ILocalizacaoRemote;

/**
 * Session Bean implementation class CadastrarLocalizacaoBean
 */
@Stateless
public class LocalizacaoBean implements ILocalizacaoRemote, ILocalizacaoLocal {

	@PersistenceContext(unitName = "SMC_UNIT")
	private EntityManager em;
	

	@Override
	public Localizacao inserirEditar(Localizacao especialidade) throws NegocioException {
		return new LocalizacaoDAO(em).inserirEditar(especialidade);
	}

	@Override
	public void deletar(Localizacao especialidade) throws NegocioException {	
		new LocalizacaoDAO(em).deletar(especialidade);
	}

	@Override
	public Localizacao buscar(Integer id) {
		return new LocalizacaoDAO(em).buscar(id);
	}

	@Override
	public List<Localizacao> listar() {
		return new LocalizacaoDAO(em).listar();
	}

	
}
