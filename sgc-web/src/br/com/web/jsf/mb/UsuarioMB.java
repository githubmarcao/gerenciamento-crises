package br.com.web.jsf.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.smc.ejb.entity.GrupoUsuario;
import br.com.smc.ejb.entity.Usuario;
import br.com.smc.ejb.exception.NegocioException;
import br.com.smc.ejb.interfaces.IGrupoUsuarioLocal;
import br.com.smc.ejb.interfaces.IUsuarioLocal;
import br.com.web.jsf.util.JSFUtil;

@ManagedBean
@RequestScoped
public class UsuarioMB {

	private Usuario usuario;
	private Usuario usuarioNaoLogado;

	@EJB
	private IUsuarioLocal usuarioLocal;

	@EJB
	private IGrupoUsuarioLocal grupoLocal;

	private List<Usuario> usuarios;

	private List<GrupoUsuario> grupos;

	@PostConstruct
	public void init(){
		usuario = new Usuario();
		usuario.setGrupo(new GrupoUsuario());
		usuarioNaoLogado = new Usuario();
		usuarioNaoLogado.setGrupo(new GrupoUsuario());
		carregarUsuarios();
		carregarGrupos();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioNaoLogado() {
		return usuarioNaoLogado;
	}

	public void setUsuarioNaoLogado(Usuario usuarioNaoLogado) {
		this.usuarioNaoLogado = usuarioNaoLogado;
	}

	public List<GrupoUsuario> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<GrupoUsuario> grupos) {
		this.grupos = grupos;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuarioSessao() {
		return (Usuario) JSFUtil.getSessionAttribute("usuario");
	}

	public String autenticar(){
		Usuario usuarioRecuperado = usuarioLocal.autenticar(usuario);
		if(usuarioRecuperado!=null){
			JSFUtil.setSessionAttribute("usuario", usuarioRecuperado);
			return "admin\\principal";
//			return "localizacao";
		}else{
			JSFUtil.addErrorMessage("Usuario ou senha nao confere.");
			return null;
		}
	}

	public String inserirEditar() {

		try {
			usuarioLocal.inserirEditar(usuarioNaoLogado);
			JSFUtil.addInfoMessage("Registro salvo com sucesso!");
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}

		return null;
	}

	public String carregarUsuarios() {

		try {
			usuarios = usuarioLocal.listar();
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}

		return "listarUsuario";
	}

	public String excluir() {

		try {
			usuarioLocal.deletar(usuarioNaoLogado);
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}
		usuarios.remove(usuarioNaoLogado);
		return "listarUsuario";
	}
	
	public List<GrupoUsuario> carregarGrupos() {
		try {
			grupos = grupoLocal.listar();
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}
		return grupos;
	}

	public String buscar() {

		try {
			usuarioNaoLogado = usuarioLocal.buscar(usuarioNaoLogado.getId());

			if(usuarioNaoLogado==null){
				JSFUtil.addErrorMessage("Registro nao localizado!");
			}
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}

		return "usuario";
	}

}
