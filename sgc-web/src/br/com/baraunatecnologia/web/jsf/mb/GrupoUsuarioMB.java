package br.com.baraunatecnologia.web.jsf.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.baraunatecnologia.smc.ejb.entity.GrupoUsuario;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.IGrupoUsuarioLocal;
import br.com.baraunatecnologia.web.jsf.util.JSFUtil;

@ManagedBean
@RequestScoped
public class GrupoUsuarioMB {
	@EJB
	private IGrupoUsuarioLocal grupoUsuarioLocal;

	private GrupoUsuario grupoUsuario;

	private List<GrupoUsuario> gruposUsuario;


	@PostConstruct
	public void init() {
		grupoUsuario = new GrupoUsuario();
		carregaGrupos();
	}

	public GrupoUsuario getGrupoUsuario() {
		return grupoUsuario;
	}

	public void setGrupoUsuario(GrupoUsuario grupoUsuario) {
		this.grupoUsuario = grupoUsuario;
	}

	public List<GrupoUsuario> getGruposUsuario() {
		return gruposUsuario;
	}

	public void setGruposUsuario(List<GrupoUsuario> gruposUsuario) {
		this.gruposUsuario = gruposUsuario;
	}

	public String inserirEditar() {

		try {
			grupoUsuarioLocal.inserirEditar(grupoUsuario);
			JSFUtil.addInfoMessage("Registro salvo com sucesso!");
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}

		return null;
	}

	public String carregaGrupos() {

		try {
			gruposUsuario = grupoUsuarioLocal.listar();
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}

		return "listarGrupoUsuario";
	}

	public String excluir() {

		try {
			grupoUsuarioLocal.deletar(grupoUsuario);
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}
		gruposUsuario.remove(grupoUsuario);
		return "listarGrupoUsuario";
	}

	public String buscar() {

		try {
			grupoUsuario = grupoUsuarioLocal.buscar(grupoUsuario.getId());

			if (grupoUsuario == null) {
				JSFUtil.addErrorMessage("Registro nao localizado!");
			}
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}

		return "grupoUsuario";
	}

}
