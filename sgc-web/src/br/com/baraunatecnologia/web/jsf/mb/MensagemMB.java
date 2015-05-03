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
	private MensagemUsuario mensagemUsuario;
	private MensagemGrupoUsuario mensagemGrupoUsuario;

	private List<Mensagem> mensagens;

	private Integer usuarioRecebidoId; // Usuario que ira receber a mensagem do centro de comando


	@PostConstruct
	public void init(){
		mensagem = new Mensagem();
		mensagem.setUsuarioEnvio(new Usuario());
		mensagemUsuario = new MensagemUsuario();
		mensagemUsuario.setUsuarioRecebido(new Usuario());
		mensagemGrupoUsuario = new MensagemGrupoUsuario();
		mensagemGrupoUsuario.setGrupoUsuarioRecebido(new GrupoUsuario());
		carregarMensagens();
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public MensagemUsuario getMensagemUsuario() {
		return mensagemUsuario;
	}

	public void setMensagemUsuario(MensagemUsuario mensagemUsuario) {
		this.mensagemUsuario = mensagemUsuario;
	}

	public MensagemGrupoUsuario getMensagemGrupoUsuario() {
		return mensagemGrupoUsuario;
	}

	public void setMensagemGrupoUsuario(MensagemGrupoUsuario mensagemGrupoUsuario) {
		this.mensagemGrupoUsuario = mensagemGrupoUsuario;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public Integer getUsuarioRecebidoId() {
		return usuarioRecebidoId;
	}

	public void setUsuarioRecebidoId(Integer usuarioRecebidoId) {
		this.usuarioRecebidoId = usuarioRecebidoId;
	}

	public String inserirEditar() {
		if (usuarioRecebidoId != null && usuarioRecebidoId > 0) {
			mensagem.getUsuarioEnvio().setId(Usuario.USUARIO_ADMINISTRADOR);
			mensagemUsuario.getUsuarioRecebido().setId(usuarioRecebidoId);
			mensagemGrupoUsuario.getGrupoUsuarioRecebido().setId(null); // Limpar para nao enviar mensagem para o grupo
		}

		try {
			mensagemLocal.inserirEditar(mensagem, mensagemUsuario, mensagemGrupoUsuario);
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
				JSFUtil.addErrorMessage("Registro n�o localizado!");
				return null;
			}

			if (mensagem.getMensagemUsuarioRecebido() == null) {
				mensagem.setMensagemUsuarioRecebido(new MensagemUsuario());
			} else {
				mensagemUsuario = mensagem.getMensagemUsuarioRecebido();
			}
			if (mensagem.getMensagemGrupoUsuarioRecebido() == null) {
				mensagem.setMensagemGrupoUsuarioRecebido(new MensagemGrupoUsuario());
			} else {
				mensagemGrupoUsuario = mensagem.getMensagemGrupoUsuarioRecebido();
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

}
