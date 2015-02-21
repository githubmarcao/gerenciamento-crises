package br.com.baraunatecnologia.web.jsf.mb;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.baraunatecnologia.smc.ejb.entity.Especialidade;
import br.com.baraunatecnologia.smc.ejb.entity.Medico;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.IEspecialidadeLocal;
import br.com.baraunatecnologia.smc.ejb.interfaces.IMedicoLocal;
import br.com.baraunatecnologia.web.jsf.util.JSFUtil;

@ManagedBean
@RequestScoped
public class MedicoMB {
	@EJB
	private IMedicoLocal medicoLocal;
	
	@EJB 
	private IEspecialidadeLocal especialidadeLocal;

	private Medico medico;

	private List<Especialidade> especialidades;
	
	private List<Medico> medicos;
	
	@PostConstruct
	public void init(){
		
		medico = new Medico(new Especialidade());
		carregarEspecialidades();
		carregarMedicos();
	}
	
	public List<Especialidade> getEspecialidades() {		
		return especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}

	public MedicoMB() {
		
	}

	public IMedicoLocal getMedicoLocal() {
		return medicoLocal;
	}

	public void setMedicoLocal(IMedicoLocal medicoLocal) {
		this.medicoLocal = medicoLocal;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public List<Medico> getMedicos() {	
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public String inserirEditar() {

		try {
			medicoLocal.inserirEditar(medico);
			JSFUtil.addInfoMessage("Registro salvo com sucesso!");
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public String carregarMedicos() {

		try {
			medicos = medicoLocal.listar();
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "listarMedico";
	}

	public String excluir() {

		try {
			
			medicoLocal.deletar(medico);
			
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "listarMedico";
	}
	
	public List<Especialidade> carregarEspecialidades() {
		   
		try {
			especialidades = especialidadeLocal.listar();
			
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return especialidades;
	}

	public String buscar() {

		try {
			medico = medicoLocal.buscar(medico.getId());

			if(medico==null){
				JSFUtil.addErrorMessage("Registro não localizado!");
			}
			
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "medico";
	}
	

}
