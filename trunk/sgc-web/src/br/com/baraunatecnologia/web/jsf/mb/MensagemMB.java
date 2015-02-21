package br.com.baraunatecnologia.web.jsf.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.baraunatecnologia.smc.ejb.entity.GrupoUsuario;
import br.com.baraunatecnologia.smc.ejb.entity.Mensagem;
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

	private List<Usuario> usuarios;

	private List<GrupoUsuario> grupos;


	@PostConstruct
	public void init(){
		mensagem = new Mensagem();
		mensagem.setUsuarioEnvio(new Usuario());
		mensagem.setUsuarioRecebido(new Usuario());
		mensagem.setGrupoRecebido(new GrupoUsuario());
		carregarMensagens();
		carregarUsuarios();
		carregarGrupoUsuarios();
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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<GrupoUsuario> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<GrupoUsuario> grupos) {
		this.grupos = grupos;
	}

	public String inserirEditar() {

		try {
			if ((mensagem.getUsuarioRecebido() == null || mensagem
					.getUsuarioRecebido().getId() == 0)
					&& (mensagem.getGrupoRecebido() == null || mensagem
							.getGrupoRecebido().getId() == 0)) {
				JSFUtil.addErrorMessage("Selecione um usuário ou um grupo para receber a mensagem.");
				return null;				
			}
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
				JSFUtil.addErrorMessage("Registro não localizado!");
				return null;
			}

			if (mensagem.getUsuarioRecebido() == null) {
				mensagem.setUsuarioRecebido(new Usuario());
			}
			if (mensagem.getGrupoRecebido() == null) {
				mensagem.setGrupoRecebido(new GrupoUsuario());
			}

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

	public List<Usuario> carregarUsuarios() {
		try {
			usuarios = usuarioLocal.listar();
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}
		return usuarios;
	}

	public List<GrupoUsuario> carregarGrupoUsuarios() {
		try {
			grupos = grupoUsuarioLocal.listar();
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}
		return grupos;
	}

}
