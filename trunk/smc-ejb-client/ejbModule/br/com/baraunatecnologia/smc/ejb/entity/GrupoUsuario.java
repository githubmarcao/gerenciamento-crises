/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baraunatecnologia.smc.ejb.entity;

import java.io.Serializable;

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

/**
 * @author Marco
 */
@Entity
@Table(name = "grupo")
@NamedQueries({
    @NamedQuery(name = "GrupoUsuario.findAll", query = "SELECT g FROM GrupoUsuario g")})
public class GrupoUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
	@SequenceGenerator(name = "seq_grupo", sequenceName = "id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_grupo")
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "icone")
    private String icone;
    @Basic(optional = false)
    @Column(name = "id_visualizacao")
    private Integer idVisualizacao;

    public static final String ICONE_PADRAO = "resources/images/usuarios/icone.png";
    public static final String NOME_USUARIO_APAGADO = "_cinza";
    public static final String NOME_USUARIO_INTERMEDIARIO = "_intermediario";
    public static final Integer ID_ADMINISTRADOR = 1;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public Integer getIdVisualizacao() {
		return idVisualizacao;
	}

	public void setIdVisualizacao(Integer idVisualizacao) {
		this.idVisualizacao = idVisualizacao;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoUsuario)) {
            return false;
        }
        GrupoUsuario other = (GrupoUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "Grupo [id=" + id + ", nome=" + nome + ", icone=" + icone + "]";
	}

}
