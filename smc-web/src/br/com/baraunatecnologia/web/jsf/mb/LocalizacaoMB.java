package br.com.baraunatecnologia.web.jsf.mb;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.baraunatecnologia.smc.ejb.entity.Localizacao;
import br.com.baraunatecnologia.smc.ejb.entity.Usuario;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.ILocalizacaoLocal;
import br.com.baraunatecnologia.web.jsf.util.JSFUtil;

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

	public void salvarLocalizacao() {
		try {
			this.localizacao.setUsuario((Usuario) JSFUtil.getSessionAttribute("usuario"));
			this.localizacao.setHorario(new Date());
			localizacaoLocal.inserirEditar(this.localizacao);
			JSFUtil.addInfoMessage("Registro salvo com sucesso!");
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
