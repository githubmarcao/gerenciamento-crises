package br.com.baraunatecnologia.web.jsf.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.baraunatecnologia.smc.ejb.entity.Paciente;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.IPacienteLocal;
import br.com.baraunatecnologia.web.jsf.util.JSFUtil;

@ManagedBean
@RequestScoped
public class PacienteMB {
	@EJB
	private IPacienteLocal pacienteLocal;

	private Paciente paciente;

	private List<Paciente> pacientes;

	public PacienteMB() {
		
	}
	
	@PostConstruct
	public void init(){
		paciente = new Paciente();
		carregaPacientes();
	}

	public IPacienteLocal getPacienteLocal() {
		return pacienteLocal;
	}

	public void setPacienteLocal(IPacienteLocal pacienteLocal) {
		this.pacienteLocal = pacienteLocal;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Paciente> getPacientes() {		
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public String inserirEditar() {

		try {
			pacienteLocal.inserirEditar(paciente);
			JSFUtil.addInfoMessage("Registro salvo com sucesso!");
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public String carregaPacientes() {

		try {
			pacientes = pacienteLocal.listar();
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "listarPaciente";
	}

	public String excluir() {

		try {
			pacienteLocal.deletar(paciente);
		
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "listarPaciente";
	}

	public String buscar() {

		try {
			paciente = pacienteLocal.buscar(paciente.getId());

			if(paciente==null){
				JSFUtil.addErrorMessage("Registro não localizado!");
			}
		
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "paciente";
	}

}
