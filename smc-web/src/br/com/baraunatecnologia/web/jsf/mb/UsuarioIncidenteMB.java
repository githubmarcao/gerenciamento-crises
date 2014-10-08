package br.com.baraunatecnologia.web.jsf.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import br.com.baraunatecnologia.smc.ejb.entity.Incidente;
import br.com.baraunatecnologia.smc.ejb.entity.Localizacao;
import br.com.baraunatecnologia.smc.ejb.interfaces.IIncidenteLocal;
import br.com.baraunatecnologia.smc.ejb.interfaces.ILocalizacaoLocal;

@ManagedBean
@RequestScoped
public class UsuarioIncidenteMB {

	private Localizacao localizacao;

	private Date dataInicio;

	private Date dataFim;

	private List<Localizacao> localizacoes;

	private List<Incidente> incidentes;

	@EJB
	private ILocalizacaoLocal localizacaoLocal;

	@EJB
	private IIncidenteLocal incidenteLocal;

	@PostConstruct
	public void init(){
		localizacao = new Localizacao();
		localizacoes = new ArrayList<Localizacao>();
		incidentes = new ArrayList<Incidente>();
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getSituacaoAtual() {
		try {
			JSONArray array = new JSONArray();

			/*
			 * Criacao do Objeto JSONObject
			 */
			localizacoes = localizacaoLocal.listarUltimaLocalizacaoUsuarios(dataInicio, dataFim);

			for (Localizacao localizacao : localizacoes) {
				JSONObject json = new JSONObject();
				json.put("idUsuario", localizacao.getUsuario().getId());
				json.put("detalhe", localizacao.getUsuario().getNome());
				json.put("icone", localizacao.getUsuario().getGrupo().getIcone());
				json.put("latitude", localizacao.getLatitude());
				json.put("longitude", localizacao.getLongitude());
				array.put(json);
			}

			incidentes = incidenteLocal.listarIncidenteIntervalo(dataInicio, dataFim);
			for (Incidente incidente : incidentes) {
				JSONObject json = new JSONObject();
				json.put("idIncidente", incidente.getLatitude());
				json.put("detalhe", incidente.getDescricao());
				json.put("icone", Incidente.ICONE_PADRAO);
				json.put("latitude", incidente.getLatitude());
				json.put("longitude", incidente.getLongitude());
				array.put(json);
			}

			return array.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

}
