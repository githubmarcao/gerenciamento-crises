package br.com.baraunatecnologia.web.jsf.mb;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.baraunatecnologia.smc.ejb.entity.Localizacao;
import br.com.baraunatecnologia.smc.ejb.entity.Usuario;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.ILocalizacaoLocal;
import br.com.baraunatecnologia.smc.ejb.interfaces.IUsuarioLocal;
import br.com.baraunatecnologia.web.jsf.util.JSFUtil;

@ManagedBean
@RequestScoped
public class LocalizacaoMB {
	@EJB
	private ILocalizacaoLocal localizacaoLocal;

	@EJB
	private IUsuarioLocal usuarioLocal;

	private Localizacao localizacao;

	private List<Localizacao> localizacoes;

	private List<Usuario> usuarios;


	@PostConstruct
	public void init(){
		localizacao = new Localizacao();
		localizacao.setUsuario(new Usuario());
		carregarLocalizacoes();
		carregarUsuarios();
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public List<Localizacao> getLocalizacoes() {
		return localizacoes;
	}

	public void setLocalizacoes(List<Localizacao> localizacoes) {
		this.localizacoes = localizacoes;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String inserirEditar() {

		try {
			localizacaoLocal.inserirEditar(localizacao);
			JSFUtil.addInfoMessage("Registro salvo com sucesso!");
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}

		return null;
	}

	public String carregarLocalizacoes() {
		try {
			localizacoes = localizacaoLocal.listar();
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}
		return "listarLocalizacoes";
	}

	public String excluir() {

		try {
			localizacaoLocal.deletar(localizacao);
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}
		localizacoes.remove(localizacao);
		return "listarLocalizacao";
	}

	public String buscar() {

		try {
			localizacao = localizacaoLocal.buscar(localizacao.getId());

			if (localizacao == null) {
				JSFUtil.addErrorMessage("Registro nao localizado!");
			}
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}

		return "localizacao";
	}

	public List<Object> getCaminhoUsuario() {
		return getCaminhoUsuario(null);
	}

	public List<Object> getCaminhoUsuario(Usuario usuario) {
		try {
			if (usuario != null) {
				// TODO
			}
			if (this.localizacao.getLatitude() != null && this.localizacao.getLongitude() != null) {
				this.localizacao.setUsuario((Usuario) JSFUtil.getSessionAttribute("usuario"));
				this.localizacao.setHorario(new Date());
				localizacaoLocal.inserirEditar(this.localizacao);
				JSFUtil.addInfoMessage("Registro salvo com sucesso!");
			}
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}
		return null;
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

}
