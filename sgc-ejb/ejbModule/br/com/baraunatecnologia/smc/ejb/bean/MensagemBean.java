package br.com.baraunatecnologia.smc.ejb.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.baraunatecnologia.smc.ejb.dao.MensagemDAO;
import br.com.baraunatecnologia.smc.ejb.dao.MensagemGrupoUsuarioDAO;
import br.com.baraunatecnologia.smc.ejb.dao.MensagemUsuarioDAO;
import br.com.baraunatecnologia.smc.ejb.entity.Mensagem;
import br.com.baraunatecnologia.smc.ejb.entity.MensagemGrupoUsuario;
import br.com.baraunatecnologia.smc.ejb.entity.MensagemUsuario;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.IMensagemLocal;
import br.com.baraunatecnologia.smc.ejb.interfaces.IMensagemRemote;

/**
 * Session Bean implementation class CadastrarMensagemBean
 */
@Stateless
public class MensagemBean implements IMensagemRemote, IMensagemLocal {

	@PersistenceContext(unitName = "SGC_UNIT")
	private EntityManager em;
	

	@Override
	public Mensagem inserirEditar(Mensagem mensagem) throws NegocioException {
		return new MensagemDAO(em).inserirEditar(mensagem);
	}

	public Mensagem inserirEditar(Mensagem mensagem, MensagemUsuario mensagemUsuario, MensagemGrupoUsuario mensagemGrupoUsuario) throws NegocioException {
		if (mensagem == null) {
			throw new NegocioException("Mensagem vazia, verifique parametros.");
		}

		if ((mensagemUsuario == null && mensagemGrupoUsuario == null)
				|| ((mensagemUsuario.getUsuarioRecebido() == null 
						&& mensagemUsuario.getUsuarioRecebido().getId() == 0) 
					&& (mensagemGrupoUsuario.getGrupoUsuarioRecebido() == null 
						&& mensagemGrupoUsuario.getGrupoUsuarioRecebido().getId() == 0))) {
			throw new NegocioException("Precisa informar quem ira receber a mensagem.");
		}

		if (mensagem.getHorarioEnvio() == null) {
			mensagem.setHorarioEnvio(new Date());
		}

		mensagem = new MensagemDAO(em).inserirEditar(mensagem);

		if (mensagem != null) {
			if (mensagemUsuario != null 
					&& mensagemUsuario.getUsuarioRecebido() != null
					&& mensagemUsuario.getUsuarioRecebido().getId() != 0) {
				mensagemUsuario.setRecebido(false);
				mensagemUsuario.setHorarioRecebido(null);
				mensagemUsuario.setMensagem(mensagem);

				mensagemUsuario = new MensagemUsuarioDAO(em).inserirEditar(mensagemUsuario);
			}
	
			if (mensagemGrupoUsuario != null 
					&& mensagemGrupoUsuario.getGrupoUsuarioRecebido() != null
					&& mensagemGrupoUsuario.getGrupoUsuarioRecebido().getId() != 0) {
				mensagemGrupoUsuario.setRecebido(false);
				mensagemGrupoUsuario.setHorarioRecebido(null);
				mensagemGrupoUsuario.setMensagem(mensagem);

				mensagemGrupoUsuario = new MensagemGrupoUsuarioDAO(em).inserirEditar(mensagemGrupoUsuario);
			}
		}
		return mensagem;
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
