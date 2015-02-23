package br.com.baraunatecnologia.web.jsf.mb;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


import br.com.baraunatecnologia.smc.ejb.entity.Especialidade;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.IEspecialidadeLocal;
import br.com.baraunatecnologia.web.jsf.util.JSFUtil;



@ManagedBean
@RequestScoped 
public class EspecialidadeMB {
	
	@EJB 
	private IEspecialidadeLocal especialidadeLocal;
	
	private Especialidade especialidade;
	
	private List<Especialidade> especialidades;
	
	public EspecialidadeMB(){
		
	}
	
	
	@PostConstruct
	public void init(){
	;
	   especialidade = new Especialidade();
	   carregarEspecialidades();	
	}
	
	
	public Especialidade getEspecialidade() {
		
		return especialidade;
	}


	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	
	public List<Especialidade> getEspecialidades() {		
		return especialidades;
	}


	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}


	public String inserirEditar(){
		
		try {
			especialidadeLocal.inserirEditar(especialidade);
			JSFUtil.addInfoMessage("Registro salvo com sucesso!");
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
    public String carregarEspecialidades(){
		
		try {
			especialidades = especialidadeLocal.listar();
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "listarEspecialidade";
	}
    
    public String excluir(){
		
		try {
	
			especialidadeLocal.deletar(especialidade);
			
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "listarEspecialidade";
	}

    public String buscar(){
		
		try {
		
			especialidade = especialidadeLocal.buscar(especialidade.getId());

			if(especialidade==null){
				JSFUtil.addErrorMessage("Registro não localizado!");
			}

		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "especialidade";
	}
}
