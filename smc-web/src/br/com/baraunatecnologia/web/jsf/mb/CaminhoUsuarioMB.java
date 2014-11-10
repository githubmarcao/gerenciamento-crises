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

import br.com.baraunatecnologia.smc.ejb.entity.Grupo;
import br.com.baraunatecnologia.smc.ejb.entity.Localizacao;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.ILocalizacaoLocal;
import br.com.baraunatecnologia.web.jsf.util.JSFUtil;

@ManagedBean
@RequestScoped
public class CaminhoUsuarioMB {

	private Integer idUsuario;

	private List<Localizacao> localizacoes;

	private Date dataInicio;

	private Date dataFim;

	@EJB
	private ILocalizacaoLocal localizacaoLocal;

	@PostConstruct
	public void init(){
		localizacoes = new ArrayList<Localizacao>();
		Integer id = null;
		try {
			String param = JSFUtil.getRequestParameter("idUsuario");
			id = Integer.parseInt(param);
		} catch (Exception e) { }
		if (id != null) {
			idUsuario = id;
		} else {
			idUsuario = 2;
		}
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
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

	public String getLocalizacoes() {
		try {
			JSONArray array = new JSONArray();

			/*
			 * Criacao do Objeto JSONObject
			 */
			localizacoes = localizacaoLocal.listar();

			for (Localizacao localizacao : localizacoes) {
				JSONObject json = new JSONObject();
				json.put("idUsuario", localizacao.getUsuario().getId());
				json.put("nomeUsuario", localizacao.getUsuario().getNome());
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
			if (dataInicio == null || dataFim == null) {
				localizacoes = localizacaoLocal.listarUltimaLocalizacaoUsuarios();
			} else {
				localizacoes = localizacaoLocal.listarUltimaLocalizacaoUsuarios(dataInicio, dataFim);
			}

			for (Localizacao localizacao : localizacoes) {
				JSONObject json = new JSONObject();
				json.put("idUsuario", localizacao.getUsuario().getId());
				json.put("detalhe", localizacao.getUsuario().getNome());
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
			JSONArray array = new JSONArray();

			/*
			 * Criacao do Objeto JSONObject
			 */
			localizacoes = localizacaoLocal.listarPorUsuario(idUsuario);
			boolean primeiraVez = true;

			for (int i = 0; i < localizacoes.size(); i++) {
				Localizacao localizacao = localizacoes.get(i);
				JSONObject json = new JSONObject();
				json.put("detalhe", localizacao.getUsuario().getNome());
				if (primeiraVez) {
					// Imagem colorida para a localizacao mais recente do usuario
					json.put("icone", localizacao.getUsuario().getGrupo().getIcone());
					primeiraVez = false;
				} else {
					if (i == localizacoes.size() - 1) {
						// Imagem preta e branca para a localizacao mais antiga do usuario
						String iconeCinza = localizacao.getUsuario().getGrupo().getIcone();
						iconeCinza = iconeCinza.replaceAll(".png", Grupo.NOME_USUARIO_APAGADO + ".png");
						json.put("icone", iconeCinza);
					} else {
						String iconeIntermediario = localizacao.getUsuario().getGrupo().getIcone();
						iconeIntermediario = iconeIntermediario.replaceAll(".png", Grupo.NOME_USUARIO_INTERMEDIARIO + ".png");
						json.put("icone", iconeIntermediario);
					}
				}
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
