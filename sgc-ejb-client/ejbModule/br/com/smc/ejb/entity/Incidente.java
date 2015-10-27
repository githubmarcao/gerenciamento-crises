package br.com.smc.ejb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "incidente")
@NamedQueries({ @NamedQuery(name = "Incidente.findAll", query = "SELECT i FROM Incidente i") })
public class Incidente implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "seq_incidente", sequenceName = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_incidente")
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
	@Basic(optional = false)
	@Column(name = "latitude")
	private Double latitude;
	@Basic(optional = false)
	@Column(name = "longitude")
	private Double longitude;
	@Basic(optional = false)
	@Column(name = "horario")
	private Date horario;

	public static String ICONE_PADRAO = "resources/images/incidente_36.png";

	public Incidente() {
	}

	public Incidente(Integer id) {
		this.id = id;
	}

	public Incidente(Integer id, String descricao, Double latitude,
			Double longitude, Date horario) {
		super();
		this.id = id;
		this.descricao = descricao;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		Incidente other = (Incidente) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Incidente [id=" + id + ", descricao=" + descricao + ", latitude="
				+ latitude + ", longitude=" + longitude + ", horario="
				+ horario + "]";
	}
}
