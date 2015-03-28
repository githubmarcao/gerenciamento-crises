package br.com.baraunatecnologia.smc.ejb.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.baraunatecnologia.smc.ejb.entity.MensagemGrupoUsuario;

public class MensagemGrupoUsuarioDAO extends GenericDAO<MensagemGrupoUsuario> {

	public MensagemGrupoUsuarioDAO(EntityManager em) {
		super(em);
	}

	@Override
	public Class<MensagemGrupoUsuario> getEntityClass() {
		return MensagemGrupoUsuario.class;
	}

	public MensagemGrupoUsuario buscarPorMensagem(Integer idMensagem) {
		Query query = super.getEntityManager().createQuery(
				"from MensagemGrupoUsuario where id_mensagem=:idMensagem "
				+ "order by horarioRecebido desc");

		query.setParameter("idMensagem", idMensagem);

		MensagemGrupoUsuario retorno = new MensagemGrupoUsuario();
		try {
			retorno = (MensagemGrupoUsuario) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

		return retorno;
	}
}
