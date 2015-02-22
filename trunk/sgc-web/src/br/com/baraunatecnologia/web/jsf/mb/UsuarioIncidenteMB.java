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

import br.com.baraunatecnologia.smc.ejb.entity.GrupoUsuario;
import br.com.baraunatecnologia.smc.ejb.entity.Incidente;
import br.com.baraunatecnologia.smc.ejb.entity.Localizacao;
import br.com.baraunatecnologia.smc.ejb.interfaces.IIncidenteLocal;
import br.com.baraunatecnologia.smc.ejb.interfaces.ILocalizacaoLocal;
import br.com.baraunatecnologia.web.jsf.util.DateUtil;

@ManagedBean
@ViewScoped
public class UsuarioIncidenteMB {

	private Localizacao localizacao;

	private List<GrupoUsuario> gruposSelecionados;

	private Map<Object, GrupoUsuario> gruposDisponiveis;

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
		gruposSelecionados = new ArrayList<>();
		gruposDisponiveis = new LinkedHashMap<Object, GrupoUsuario>();
		localizacoes = new ArrayList<Localizacao>();
		incidentes = new ArrayList<Incidente>();
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public List<GrupoUsuario> getGruposSelecionados() {
		return gruposSelecionados;
	}

	public void setGruposSelecionados(List<GrupoUsuario> gruposSelecionados) {
		this.gruposSelecionados = gruposSelecionados;
	}

	public Map<Object, GrupoUsuario> getGruposDisponiveis() {
		return gruposDisponiveis;
	}

	public void setGruposDisponiveis(Map<Object, GrupoUsuario> gruposDisponiveis) {
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

			localizacoes = localizacaoLocal.listarUltimaLocalizacaoUsuarios(dataInicio, dataFim);

			for (Localizacao localizacao : localizacoes) {
				GrupoUsuario grupo = localizacao.getUsuario().getGrupo();
				String nomeGrupo = grupo.getNome();

				// Carragar na tela todos os grupos, o filtro será feito na tela agora
					JSONObject json = new JSONObject();
					json.put("idUsuario", localizacao.getUsuario().getId());
					json.put("idGrupo", grupo.getId());
					json.put("nomeGrupo", grupo.getNome());
					json.put("nomeUsuario", localizacao.getUsuario().getNome());
					json.put("icone", grupo.getIconePequeno());
					json.put("latitude", localizacao.getLatitude());
					json.put("longitude", localizacao.getLongitude());
					json.put("horario", DateUtil.timestampToString(localizacao.getHorario()));
					array.put(json);

				// Salvar grupo no filtro
				if (!gruposDisponiveis.containsKey(nomeGrupo)) {
					gruposDisponiveis.put(nomeGrupo, grupo);
				}
			}

			incidentes = incidenteLocal.listarIncidenteIntervalo(dataInicio, dataFim);
			for (Incidente incidente : incidentes) {
				JSONObject json = new JSONObject();
				json.put("idIncidente", incidente.getId());
				json.put("descricaoIncidente", incidente.getDescricao());
				json.put("icone", Incidente.ICONE_PADRAO);
				json.put("latitude", incidente.getLatitude());
				json.put("longitude", incidente.getLongitude());
				json.put("horario", DateUtil.timestampToString(incidente.getHorario()));
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
