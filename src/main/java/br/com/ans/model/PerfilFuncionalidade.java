package br.com.ans.model;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ta_perfil_funcionalidade", schema="loja")
@Dependent
public class PerfilFuncionalidade implements EntidadeBase, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public PerfilFuncionalidade(){}
	
	@Id
	@Column(name="id_perfil_funcionalidade", nullable=false)
	private Long codigoPerfilFuncionalidade;
	
	@Override
	public Long getId() {
		return codigoPerfilFuncionalidade;
	}
	
	@ManyToOne
	@JoinColumn(name = "id_perfil", insertable = false, updatable = false)
	private Perfil perfil;

	@ManyToOne
	@JoinColumn(name = "id_funcionalidade", insertable = false, updatable = false)
	private Funcionalidade funcionalidade;

	public Long getCodigoPerfilFuncionalidade() {
		return codigoPerfilFuncionalidade;
	}

	public void setCodigoPerfilFuncionalidade(Long codigoPerfilFuncionalidade) {
		this.codigoPerfilFuncionalidade = codigoPerfilFuncionalidade;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Funcionalidade getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codigoPerfilFuncionalidade == null) ? 0
						: codigoPerfilFuncionalidade.hashCode());
		result = prime * result
				+ ((funcionalidade == null) ? 0 : funcionalidade.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerfilFuncionalidade other = (PerfilFuncionalidade) obj;
		if (codigoPerfilFuncionalidade == null) {
			if (other.codigoPerfilFuncionalidade != null)
				return false;
		} else if (!codigoPerfilFuncionalidade
				.equals(other.codigoPerfilFuncionalidade))
			return false;
		if (funcionalidade == null) {
			if (other.funcionalidade != null)
				return false;
		} else if (!funcionalidade.equals(other.funcionalidade))
			return false;
		if (perfil == null) {
			if (other.perfil != null)
				return false;
		} else if (!perfil.equals(other.perfil))
			return false;
		return true;
	}
	
}
