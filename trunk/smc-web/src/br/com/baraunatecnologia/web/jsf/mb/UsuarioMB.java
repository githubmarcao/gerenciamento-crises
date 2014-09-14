package br.com.baraunatecnologia.web.jsf.mb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import br.com.baraunatecnologia.smc.ejb.entity.Usuario;
import br.com.baraunatecnologia.smc.ejb.interfaces.IUsuarioLocal;
import br.com.baraunatecnologia.web.jsf.util.JSFUtil;

@ManagedBean
@RequestScoped
public class UsuarioMB {
	
	private Usuario usuario;
	private Integer total = 2; // Total do array que ira percorrer os usuarios
	
	@EJB
	private IUsuarioLocal usuarioLocal;

	@PostConstruct
	public void init(){
		usuario = new Usuario();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuarioSessao() {
		return (Usuario) JSFUtil.getSessionAttribute("usuario");
	}

	public String autenticar(){
		Usuario usuarioRecuperado = usuarioLocal.autenticar(usuario);
		if(usuarioRecuperado!=null){
			JSFUtil.setSessionAttribute("usuario", usuarioRecuperado);
			//return "admin\\principal";
			return "localizacao";
		}else{
			JSFUtil.addErrorMessage("Usuario ou senha não confere");
			return null;
		}
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String arrayToString() {
		try {
			JSONArray array = new JSONArray();

			/*
			 * Criação do Objeto JSONObject
			 */
			JSONObject jsonOne = new JSONObject();

			jsonOne.put("nome", "FlaviaJose");
			jsonOne.put("idade", 10);

			JSONObject jsonTwo = new JSONObject();

			jsonTwo.put("nome", "JandroSales");
			jsonTwo.put("idade", 43);

			array.put(jsonOne);
			array.put(jsonTwo);

			return array.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

}
