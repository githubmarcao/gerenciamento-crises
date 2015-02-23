package br.com.baraunatecnologia.web.jsf.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.baraunatecnologia.smc.ejb.entity.Consulta;
import br.com.baraunatecnologia.smc.ejb.entity.Medico;
import br.com.baraunatecnologia.smc.ejb.entity.Paciente;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.IConsultaLocal;
import br.com.baraunatecnologia.smc.ejb.interfaces.IMedicoLocal;
import br.com.baraunatecnologia.smc.ejb.interfaces.IPacienteLocal;
import br.com.baraunatecnologia.web.jsf.util.JSFUtil;

@ManagedBean
@SessionScoped
public class ConsultaMB {

	@EJB
	private IConsultaLocal consultaLocal;
	@EJB
	private IMedicoLocal medicoLocal;
	@EJB
	private IPacienteLocal pacienteLocal;

	private Consulta consulta;

	private List<Consulta> consultas;
	private Paciente paciente;
		
	@PostConstruct
	public void init(){
		consulta = new Consulta(new Medico(), new Paciente());
		carregaConsulta();
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}


	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	public String selecionarMedico() {

		try {
			consulta.setMedico(medicoLocal.buscar(Integer.parseInt(JSFUtil.getRequestParameter("idMedico"))));
			
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public String selecionarPaciente() {

		try {
			consulta.setPaciente(pacienteLocal.buscar(Integer.parseInt(JSFUtil.getRequestParameter("idPaciente"))));
			
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public String inserirEditar() {

		try {
			consultaLocal.inserirEditar(consulta);
			JSFUtil.addInfoMessage("Registro salvo com sucesso!");
			carregaConsulta();
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public String excluir() {

		try {
			consultaLocal.deletar(consulta);
			JSFUtil.addInfoMessage("Registro exclido com sucesso!");
			carregaConsulta();
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "listarConsulta";
	}

	public String buscar() {

		try {
			consulta = consultaLocal.buscar(consulta.getId());

			if(consulta==null){
				JSFUtil.addErrorMessage("Registro não localizado!");
			}
		
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "consulta";
	}

	public String carregaConsulta() {

		try {
			consultas = consultaLocal.listar();
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "listarConsulta";
	}
	
	public String preparaCadastro(){
		JSFUtil.resetManagedBean("consultaMB");
		consulta = new Consulta(new Medico(), new Paciente());
		return "consulta";
	}


}
