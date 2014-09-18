package br.com.baraunatecnologia.web.jsf.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import br.com.baraunatecnologia.smc.ejb.entity.Localizacao;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.ILocalizacaoLocal;

@ManagedBean
@RequestScoped
public class CaminhoUsuarioMB {
	
	private List<Localizacao> localizacoes;
	
	@EJB
	private ILocalizacaoLocal localizacaoLocal;

	@PostConstruct
	public void init(){
		localizacoes = new ArrayList<Localizacao>();
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

	public String getCaminhoUsuario(Integer idUsuario) {
		try {
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

			return array.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

}
