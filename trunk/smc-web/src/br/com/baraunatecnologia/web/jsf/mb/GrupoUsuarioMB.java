package br.com.baraunatecnologia.web.jsf.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.baraunatecnologia.smc.ejb.entity.Grupo;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.IGrupoLocal;
import br.com.baraunatecnologia.web.jsf.util.JSFUtil;

@ManagedBean
@RequestScoped
public class GrupoUsuarioMB {
	@EJB
	private IGrupoLocal grupoLocal;

	private Grupo grupoUsuario;

	private List<Grupo> gruposUsuario;

	public GrupoUsuarioMB() {

	}

	@PostConstruct
	public void init() {
		grupoUsuario = new Grupo();
		carregaGrupos();
	}

	public IGrupoLocal getGrupoLocal() {
		return grupoLocal;
	}

	public void setGrupoLocal(IGrupoLocal grupoLocal) {
		this.grupoLocal = grupoLocal;
	}

	public Grupo getGrupoUsuario() {
		return grupoUsuario;
	}

	public void setGrupoUsuario(Grupo grupoUsuario) {
		this.grupoUsuario = grupoUsuario;
	}

	public List<Grupo> getGruposUsuario() {
		return gruposUsuario;
	}

	public void setGruposUsuario(List<Grupo> gruposUsuario) {
		this.gruposUsuario = gruposUsuario;
	}

	public String inserirEditar() {

		try {
			grupoLocal.inserirEditar(grupoUsuario);
			JSFUtil.addInfoMessage("Registro salvo com sucesso!");
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public String carregaGrupos() {

		try {
			gruposUsuario = grupoLocal.listar();
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "listarGrupo";
	}

	public String excluir() {

		try {
			grupoLocal.deletar(grupoUsuario);

		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "listarGrupo";
	}

	public String buscar() {

		try {
			grupoUsuario = grupoLocal.buscar(grupoUsuario.getId());

			if (grupoUsuario == null) {
				JSFUtil.addErrorMessage("Registro não localizado!");
			}

		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "grupo";
	}

}
