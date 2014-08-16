package br.com.baraunatecnologia.web.jsf.mb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.baraunatecnologia.smc.ejb.entity.Localizacao;
import br.com.baraunatecnologia.smc.ejb.interfaces.ILocalizacaoLocal;

@ManagedBean
@RequestScoped
public class LocalizacaoMB {
	
	private Localizacao localizacao;
	
	@EJB
	private ILocalizacaoLocal localizacaoLocal;

	@PostConstruct
	public void init(){
		localizacao = new Localizacao();
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public String autenticar(){			
//		Usuario usuarioRecuperado = usuarioLocal.autenticar(localizacao);
//		if(usuarioRecuperado!=null){
//			JSFUtil.setSessionAttribute("usuario", usuarioRecuperado);
//			//return "admin\\principal";
//			return "login_localizacao";
//		}else{
//			JSFUtil.addErrorMessage("Usuario ou senha n�o confere");
//			return null;
//		}
		return "";
	}
	
	

}
