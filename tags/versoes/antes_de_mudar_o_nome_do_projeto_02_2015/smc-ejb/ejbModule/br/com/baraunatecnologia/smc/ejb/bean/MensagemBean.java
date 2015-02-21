package br.com.baraunatecnologia.smc.ejb.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.baraunatecnologia.smc.ejb.dao.MensagemDAO;
import br.com.baraunatecnologia.smc.ejb.entity.Mensagem;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.IMensagemLocal;
import br.com.baraunatecnologia.smc.ejb.interfaces.IMensagemRemote;

/**
 * Session Bean implementation class CadastrarMensagemBean
 */
@Stateless
public class MensagemBean implements IMensagemRemote, IMensagemLocal {

	@PersistenceContext(unitName = "SMC_UNIT")
	private EntityManager em;
	

	@Override
	public Mensagem inserirEditar(Mensagem mensagem) throws NegocioException {
		if (mensagem != null) {
			if (mensagem.getUsuarioRecebido() != null && mensagem.getUsuarioRecebido().getId() == 0) {
				mensagem.setUsuarioRecebido(null);
			}
	
			if (mensagem.getGrupoRecebido() != null && mensagem.getGrupoRecebido().getId() == 0) {
				mensagem.setGrupoRecebido(null);
			}
	
			if (mensagem.getRecebido() == null) {
				mensagem.setRecebido(false);
			}
	
			if (mensagem.getHorarioEnvio() == null) {
				mensagem.setHorarioEnvio(new Date());
			}
		}
		return new MensagemDAO(em).inserirEditar(mensagem);
	}

	@Override
	public void deletar(Mensagem mensagem) throws NegocioException {	
		new MensagemDAO(em).deletar(mensagem);
	}

	@Override
	public Mensagem buscar(Integer id) {
		return new MensagemDAO(em).buscar(id);
	}

	@Override
	public List<Mensagem> listar() {
		return new MensagemDAO(em).listar();
	}
}
