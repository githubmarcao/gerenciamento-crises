package br.com.baraunatecnologia.web.jsf.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.baraunatecnologia.smc.ejb.entity.Incidente;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.IIncidenteLocal;
import br.com.baraunatecnologia.web.jsf.util.JSFUtil;

@ManagedBean
@RequestScoped
public class IncidenteMB {

	@EJB
	private IIncidenteLocal incidenteLocal;

	private Incidente incidente;

	private List<Incidente> incidentes;


	@PostConstruct
	public void init(){
		incidente = new Incidente();
		carregarIncidentes();
	}

	public Incidente getIncidente() {
		return incidente;
	}

	public void setIncidente(Incidente incidente) {
		this.incidente = incidente;
	}

	public List<Incidente> getIncidentes() {
		return incidentes;
	}

	public void setIncidentes(List<Incidente> incidentes) {
		this.incidentes = incidentes;
	}
	public String inserirEditar() {
		try {
			incidenteLocal.inserirEditar(incidente);
			JSFUtil.addInfoMessage("Registro salvo com sucesso!");
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}
		return null;
	}

	public String excluir() {
		try {
			incidenteLocal.deletar(incidente);
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}
		incidentes.remove(incidente);
		return "listarIncidente";
	}

	public String buscar() {
		try {
			incidente = incidenteLocal.buscar(incidente.getId());

			if (incidente == null) {
				JSFUtil.addErrorMessage("Registro nao localizado!");
				return null;
			}
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}
		return "incidente";
	}

	private String carregarIncidentes() {
		try {
			incidentes = incidenteLocal.listar();
		} catch (NegocioException e) {
			JSFUtil.addErrorMessage(e.getMessage());
			return null;
		}
		return "listarMensagens";
	}

}
