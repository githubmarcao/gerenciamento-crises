package br.com.smc.ejb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "mensagem_grupo_usuario")
@NamedQueries({ @NamedQuery(name = "MensagemGrupoUsuario.findAll", query = "SELECT l FROM MensagemGrupoUsuario l") })
public class MensagemGrupoUsuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_mensagem_grupo_usuario", sequenceName = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mensagem_grupo_usuario")
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@JoinColumn(name = "id_mensagem", referencedColumnName = "id")
	@OneToOne
	private Mensagem mensagem;

	@JoinColumn(name = "id_grupo_usuario_recebido", referencedColumnName = "id", nullable = true)
	@OneToOne
	private GrupoUsuario grupoUsuarioRecebido;

	@Basic(optional = false)
	@Column(name = "horario_recebido")
	private Date horarioRecebido;

	@Basic(optional = false)
	@Column(name = "recebido")
	private Boolean recebido;


	public MensagemGrupoUsuario() {
	}

	public MensagemGrupoUsuario(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public GrupoUsuario getGrupoUsuarioRecebido() {
		return grupoUsuarioRecebido;
	}

	public void setGrupoUsuarioRecebido(GrupoUsuario grupoUsuarioRecebido) {
		this.grupoUsuarioRecebido = grupoUsuarioRecebido;
	}

	public Boolean getRecebido() {
		return recebido;
	}

	public void setRecebido(Boolean recebido) {
		this.recebido = recebido;
	}

	public Date getHorarioRecebido() {
		return horarioRecebido;
	}

	public void setHorarioRecebido(Date horarioRecebido) {
		this.horarioRecebido = horarioRecebido;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Usuario)) {
			return false;
		}
		MensagemGrupoUsuario other = (MensagemGrupoUsuario) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MensagemUsuario [id=" + id + ", mensagem=" + mensagem
				+ ", grupoUsuarioRecebido=" + grupoUsuarioRecebido + ", recebido="
				+ recebido + ", horarioRecebido=" + horarioRecebido + "]";
	}

}
