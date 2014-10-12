package br.com.baraunatecnologia.web.jsf.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import br.com.baraunatecnologia.smc.ejb.entity.Incidente;
import br.com.baraunatecnologia.smc.ejb.entity.Localizacao;
import br.com.baraunatecnologia.smc.ejb.enumerator.GrupoUsuarioClassEnum.GrupoUsuarioEnum;
import br.com.baraunatecnologia.smc.ejb.interfaces.IIncidenteLocal;
import br.com.baraunatecnologia.smc.ejb.interfaces.ILocalizacaoLocal;

@ManagedBean
@ViewScoped
public class UsuarioIncidenteMB {

	private Localizacao localizacao;

	private List<GrupoUsuarioEnum> gruposSelecionados;

	private Map<Object, GrupoUsuarioEnum> gruposDisponiveis;

	private Date dataInicio;

	private Date dataFim;

	private List<Localizacao> localizacoes;

	private List<Incidente> incidentes;

	@EJB
	private ILocalizacaoLocal localizacaoLocal;

	@EJB
	private IIncidenteLocal incidenteLocal;
	
	@PostConstruct
	public void init() {
		localizacao = new Localizacao();
		gruposSelecionados = new ArrayList<GrupoUsuarioEnum>();
		gruposDisponiveis = new LinkedHashMap<Object, GrupoUsuarioEnum>();
		localizacoes = new ArrayList<Localizacao>();
		incidentes = new ArrayList<Incidente>();
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public List<GrupoUsuarioEnum> getGruposSelecionados() {
		return gruposSelecionados;
	}

	public void setGruposSelecionados(List<GrupoUsuarioEnum> gruposSelecionados) {
		this.gruposSelecionados = gruposSelecionados;
	}

	public Map<Object, GrupoUsuarioEnum> getGruposDisponiveis() {
		return gruposDisponiveis;
	}

	public void setGruposDisponiveis(Map<Object, GrupoUsuarioEnum> gruposDisponiveis) {
		this.gruposDisponiveis = gruposDisponiveis;
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
				Integer idGrupo = localizacao.getUsuario().getGrupo().getId();
				GrupoUsuarioEnum grupoUsuarioEnum = GrupoUsuarioEnum.getById(idGrupo);

				// Carragar na tela apenas os grupos selecionados
				if (gruposSelecionados == null
						|| gruposSelecionados.size() <= 0
						|| gruposSelecionados.contains(grupoUsuarioEnum)) {
					JSONObject json = new JSONObject();
					json.put("idUsuario", localizacao.getUsuario().getId());
					json.put("idGrupo", idGrupo);
					json.put("detalhe", localizacao.getUsuario().getNome());
					json.put("icone", localizacao.getUsuario().getGrupo().getIcone());
					json.put("latitude", localizacao.getLatitude());
					json.put("longitude", localizacao.getLongitude());
					array.put(json);
				}

				// Salvar grupo no filtro
				if (!gruposDisponiveis.containsKey(grupoUsuarioEnum)) {
					gruposDisponiveis.put(idGrupo, grupoUsuarioEnum);
				}
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
