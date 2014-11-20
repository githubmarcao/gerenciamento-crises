package br.com.baraunatecnologia.web.jsf.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.baraunatecnologia.smc.ejb.entity.Grupo;
import br.com.baraunatecnologia.smc.ejb.entity.Usuario;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.IGrupoLocal;
import br.com.baraunatecnologia.smc.ejb.interfaces.IUsuarioLocal;
import br.com.baraunatecnologia.web.jsf.util.JSFUtil;

@ManagedBean
@RequestScoped
public class UsuarioMB {

	private Usuario usuario;
	private Usuario usuarioNaoLogado;

	@EJB
	private IUsuarioLocal usuarioLocal;

	@EJB
	private IGrupoLocal grupoLocal;

	private List<Usuario> usuarios;

	private List<Grupo> grupos;

	@PostConstruct
	public void init(){
		usuario = new Usuario();
		usuario.setGrupo(new Grupo());
		usuarioNaoLogado = new Usuario();
		usuarioNaoLogado.setGrupo(new Grupo());
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

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
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
			JSFUtil.addErrorMessage("Usuario ou senha não confere.");
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

		return "listarUsuario";
	}
	
	public List<Grupo> carregarGrupos() {
		   
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
				JSFUtil.addErrorMessage("Registro não localizado!");
			}
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}

		return "usuario";
	}

}
