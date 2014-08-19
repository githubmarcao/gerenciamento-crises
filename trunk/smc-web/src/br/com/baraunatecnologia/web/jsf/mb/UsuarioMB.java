package br.com.baraunatecnologia.web.jsf.mb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.baraunatecnologia.smc.ejb.entity.Usuario;
import br.com.baraunatecnologia.smc.ejb.interfaces.IUsuarioLocal;
import br.com.baraunatecnologia.web.jsf.util.JSFUtil;

@ManagedBean
@RequestScoped
public class UsuarioMB {
	
	private Usuario usuario;
	
	@EJB
	private IUsuarioLocal usuarioLocal;

	@PostConstruct
	public void init(){
		usuario = new Usuario();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuarioSessao() {
		return (Usuario) JSFUtil.getSessionAttribute("usuario");
	}

	public String autenticar(){			
		Usuario usuarioRecuperado = usuarioLocal.autenticar(usuario);
		if(usuarioRecuperado!=null){
			JSFUtil.setSessionAttribute("usuario", usuarioRecuperado);
			//return "admin\\principal";
			return "localizacao";
		}else{
			JSFUtil.addErrorMessage("Usuario ou senha não confere");
			return null;
		}
	}

}
