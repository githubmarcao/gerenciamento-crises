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
import javax.persistence.Transient;

@Entity
@Table(name = "mensagem")
@NamedQueries({ @NamedQuery(name = "Mensagem.findAll", query = "SELECT l FROM Mensagem l") })
public class Mensagem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_mensagem", sequenceName = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mensagem")
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@JoinColumn(name = "id_usuario_envio", referencedColumnName = "id")
	@OneToOne
	private Usuario usuarioEnvio;

	@Basic(optional = false)
	@Column(name = "mensagem")
	private String mensagem;

	@Basic(optional = false)
	@Column(name = "horario_envio")
	private Date horarioEnvio;

	@Transient
	private MensagemUsuario mensagemUsuarioRecebido;

	@Transient
	private MensagemGrupoUsuario mensagemGrupoUsuarioRecebido;

	
	
	public Mensagem() {
	}

	public Mensagem(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuarioEnvio() {
		return usuarioEnvio;
	}

	public void setUsuarioEnvio(Usuario usuarioEnvio) {
		this.usuarioEnvio = usuarioEnvio;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getHorarioEnvio() {
		return horarioEnvio;
	}

	public void setHorarioEnvio(Date horarioEnvio) {
		this.horarioEnvio = horarioEnvio;
	}

	public MensagemUsuario getMensagemUsuarioRecebido() {
		return mensagemUsuarioRecebido;
	}

	public void setMensagemUsuarioRecebido(MensagemUsuario mensagemUsuarioRecebido) {
		this.mensagemUsuarioRecebido = mensagemUsuarioRecebido;
	}

	public MensagemGrupoUsuario getMensagemGrupoUsuarioRecebido() {
		return mensagemGrupoUsuarioRecebido;
	}

	public void setMensagemGrupoUsuarioRecebido(
			MensagemGrupoUsuario mensagemGrupoUsuarioRecebido) {
		this.mensagemGrupoUsuarioRecebido = mensagemGrupoUsuarioRecebido;
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
		Mensagem other = (Mensagem) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Mensagem [id=" + id + ", usuarioEnvio=" + usuarioEnvio
				+ ", mensagemUsuarioRecebido=" + mensagemUsuarioRecebido
				+ ", mensagemGrupoUsuarioRecebido="
				+ mensagemGrupoUsuarioRecebido + ", mensagem=" + mensagem
				+ ", horarioEnvio=" + horarioEnvio + "]";
	}
	
}
