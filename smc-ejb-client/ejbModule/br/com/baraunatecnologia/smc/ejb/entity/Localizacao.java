package br.com.baraunatecnologia.smc.ejb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "localizacao")
@NamedQueries({ @NamedQuery(name = "Localizacao.findAll", query = "SELECT l FROM Localizacao l") })
public class Localizacao implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@JoinColumn(name = "id_usuario", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Usuario usuario;

	@Basic(optional = false)
	@Column(name = "latitude")
	private Double latitude;
	@Basic(optional = false)
	@Column(name = "longitude")
	private Double longitude;
	@Basic(optional = false)
	@Column(name = "horario")
	private Date horario;

	public Localizacao() {
	}

	public Localizacao(Integer id) {
		this.id = id;
	}

	public Localizacao(Integer id, Usuario usuario, Double latitude,
			Double longitude, Date horario) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.latitude = latitude;
		this.longitude = longitude;
		this.horario = horario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
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
		Localizacao other = (Localizacao) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Localizacao [id=" + id + ", usuario=" + usuario + ", latitude="
				+ latitude + ", longitude=" + longitude + ", horario="
				+ horario + "]";
	}
}
