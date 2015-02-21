package br.com.baraunatecnologia.smc.ejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.baraunatecnologia.smc.ejb.dao.ConsultaDAO;
import br.com.baraunatecnologia.smc.ejb.entity.Consulta;
import br.com.baraunatecnologia.smc.ejb.exception.NegocioException;
import br.com.baraunatecnologia.smc.ejb.interfaces.IConsultaLocal;
import br.com.baraunatecnologia.smc.ejb.interfaces.IConsultaRemote;

/**
 * Session Bean implementation class CadastrarConsultaBean
 */
@Stateless
public class ConsultaBean implements IConsultaRemote, IConsultaLocal {

	@PersistenceContext(unitName = "SMC_UNIT")
	private EntityManager em;
	

	@Override
	public Consulta inserirEditar(Consulta consulta) throws NegocioException {
		return new ConsultaDAO(em).inserirEditar(consulta);
	}

	@Override
	public void deletar(Consulta consulta) throws NegocioException {	
		new ConsultaDAO(em).deletar(consulta);
	}

	@Override
	public Consulta buscar(Integer id) {
		return new ConsultaDAO(em).buscar(id);
	}

	@Override
	public List<Consulta> listar() {
		return new ConsultaDAO(em).listar();
	}

	
}
