package br.com.baraunatecnologia.web.jsf.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import br.com.baraunatecnologia.smc.ejb.entity.Localizacao;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.ILocalizacaoLocal;

@ManagedBean
@SessionScoped
public class UsuarioIncidenteMB {

	private Integer idUsuario;

	private List<Localizacao> localizacoes;

	@EJB
	private ILocalizacaoLocal localizacaoLocal;

	@PostConstruct
	public void init(){
		localizacoes = new ArrayList<Localizacao>();
		idUsuario = 2;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLocalizacoes() {
		try {
			JSONArray array = new JSONArray();

			/*
			 * Criacao do Objeto JSONObject
			 */
			localizacoes = localizacaoLocal.listar();

			for (Localizacao localizacao : localizacoes) {
				JSONObject json = new JSONObject();
				json.put("usuario", localizacao.getUsuario().getNome());
				json.put("icone", localizacao.getUsuario().getGrupo().getIcone());
				json.put("latitude", localizacao.getLatitude());
				json.put("longitude", localizacao.getLongitude());
				array.put(json);
			}

			return array.toString();
		} catch (JSONException | NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public String getUltimaLocalizacaoUsuarios() {
		try {
			JSONArray array = new JSONArray();

			/*
			 * Criacao do Objeto JSONObject
			 */
			localizacoes = localizacaoLocal.listarUltimaLocalizacaoUsuarios();

			for (Localizacao localizacao : localizacoes) {
				JSONObject json = new JSONObject();
				json.put("idUsuario", localizacao.getUsuario().getId());
				json.put("usuario", localizacao.getUsuario().getNome());
				json.put("icone", localizacao.getUsuario().getGrupo().getIcone());
				json.put("latitude", localizacao.getLatitude());
				json.put("longitude", localizacao.getLongitude());
				array.put(json);
			}

			return array.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public String getSituacaoAtual() {
		try {
			JSONArray array = new JSONArray();

			/*
			 * Criacao do Objeto JSONObject
			 */
			localizacoes = localizacaoLocal.listarUltimaLocalizacaoUsuarios();

			for (Localizacao localizacao : localizacoes) {
				JSONObject json = new JSONObject();
				json.put("idUsuario", localizacao.getUsuario().getId());
				json.put("usuario", localizacao.getUsuario().getNome());
				json.put("icone", localizacao.getUsuario().getGrupo().getIcone());
				json.put("latitude", localizacao.getLatitude());
				json.put("longitude", localizacao.getLongitude());
				array.put(json);
			}

			return array.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public String getCaminhoUsuario() {
		try {
			System.out.println("getCaminhoUsuario, idUsuario: "+idUsuario);
			JSONArray array = new JSONArray();

			/*
			 * Criacao do Objeto JSONObject
			 */
			localizacoes = localizacaoLocal.listarPorUsuario(idUsuario);

			for (Localizacao localizacao : localizacoes) {
				JSONObject json = new JSONObject();
				json.put("usuario", localizacao.getUsuario().getNome());
				json.put("icone", localizacao.getUsuario().getGrupo().getIcone());
				json.put("latitude", localizacao.getLatitude());
				json.put("longitude", localizacao.getLongitude());
				array.put(json);
			}

			System.out.println(array.toString());
			return array.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

}
