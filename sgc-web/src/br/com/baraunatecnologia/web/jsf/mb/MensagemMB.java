package br.com.baraunatecnologia.web.jsf.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.baraunatecnologia.smc.ejb.entity.GrupoUsuario;
import br.com.baraunatecnologia.smc.ejb.entity.Mensagem;
import br.com.baraunatecnologia.smc.ejb.entity.MensagemGrupoUsuario;
import br.com.baraunatecnologia.smc.ejb.entity.MensagemUsuario;
import br.com.baraunatecnologia.smc.ejb.entity.Usuario;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.IGrupoUsuarioLocal;
import br.com.baraunatecnologia.smc.ejb.interfaces.IMensagemLocal;
import br.com.baraunatecnologia.smc.ejb.interfaces.IUsuarioLocal;
import br.com.baraunatecnologia.web.jsf.util.JSFUtil;

@ManagedBean
@RequestScoped
public class MensagemMB {
	@EJB
	private IMensagemLocal mensagemLocal;

	@EJB
	private IUsuarioLocal usuarioLocal;
	@EJB
	private IGrupoUsuarioLocal grupoUsuarioLocal;

	private Mensagem mensagem;

	private List<Mensagem> mensagens;


	@PostConstruct
	public void init(){
		mensagem = new Mensagem();
		mensagem.setUsuarioEnvio(new Usuario());
		MensagemUsuario msgUsuario = new MensagemUsuario();
		msgUsuario.setUsuarioRecebido(new Usuario());
		mensagem.setMensagemUsuarioRecebido(msgUsuario);
		MensagemGrupoUsuario msgGrupoUsuario = new MensagemGrupoUsuario();
		msgGrupoUsuario.setGrupoUsuarioRecebido(new GrupoUsuario());
		mensagem.setMensagemGrupoUsuarioRecebido(msgGrupoUsuario);
		carregarMensagens();
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public String inserirEditar() {

		try {
//			if ((mensagem.getUsuarioRecebido() == null || mensagem
//					.getUsuarioRecebido().getId() == 0)
//					&& (mensagem.getGrupoRecebido() == null || mensagem
//							.getGrupoRecebido().getId() == 0)) {
//				JSFUtil.addErrorMessage("Selecione um usu√°rio ou um grupo para receber a mensagem.");
//				return null;				
//			}
			mensagemLocal.inserirEditar(mensagem);
			JSFUtil.addInfoMessage("Registro salvo com sucesso!");
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}

		return null;
	}

	public String excluir() {

		try {
			mensagemLocal.deletar(mensagem);
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}
		mensagens.remove(mensagem);
		return "listarMensagem";
	}

	public String buscar() {

		try {
			mensagem = mensagemLocal.buscar(mensagem.getId());

			if (mensagem == null) {
				JSFUtil.addErrorMessage("Registro n„o localizado!");
				return null;
			}

//			if (mensagem.getUsuarioRecebido() == null) {
//				mensagem.setUsuarioRecebido(new Usuario());
//			}
//			if (mensagem.getGrupoRecebido() == null) {
//				mensagem.setGrupoRecebido(new GrupoUsuario());
//			}

		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}

		return "mensagem";
	}

	private String carregarMensagens() {
		try {
			mensagens = mensagemLocal.listar();
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}
		return "listarMensagens";
	}

}
