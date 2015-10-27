package br.com.smc.ejb.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.smc.ejb.entity.MensagemUsuario;

public class MensagemUsuarioDAO extends GenericDAO<MensagemUsuario> {

	public MensagemUsuarioDAO(EntityManager em) {
		super(em);
	}

	@Override
	public Class<MensagemUsuario> getEntityClass() {
		return MensagemUsuario.class;
	}
	
	public MensagemUsuario buscarPorMensagem(Integer idMensagem) {
		Query query = super.getEntityManager().createQuery(
				"from MensagemUsuario where id_mensagem=:idMensagem "
				+ "order by horarioRecebido desc");

		query.setParameter("idMensagem", idMensagem);

		MensagemUsuario retorno = new MensagemUsuario();
		try {
			retorno = (MensagemUsuario) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

		return retorno;
	}
}
