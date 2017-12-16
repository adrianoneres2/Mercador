package br.com.ans.model;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_funcionalidade", schema="loja")
@Dependent
public class Funcionalidade implements EntidadeBase, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Funcionalidade(){}
	
	@Id
	@Column(name="id_funcionalidade", nullable=false)
	private Long codigoFuncionalidade;
	
	@Column(name="nm_funcionalidade", nullable=false)
	private String nomeFuncionalidade;
	
	@OneToMany(mappedBy = "perfil")
	private Collection<PerfilFuncionalidade> perfilFuncionalidade;
	
	@Override
	public Long getId() {
		return codigoFuncionalidade;
	}


	public Long getCodigoFuncionalidade() {
		return codigoFuncionalidade;
	}


	public void setCodigoFuncionalidade(Long codigoFuncionalidade) {
		this.codigoFuncionalidade = codigoFuncionalidade;
	}


	public String getNomeFuncionalidade() {
		return nomeFuncionalidade;
	}


	public void setNomeFuncionalidade(String nomeFuncionalidade) {
		this.nomeFuncionalidade = nomeFuncionalidade;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codigoFuncionalidade == null) ? 0 : codigoFuncionalidade
						.hashCode());
		result = prime
				* result
				+ ((nomeFuncionalidade == null) ? 0 : nomeFuncionalidade
						.hashCode());
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
		Funcionalidade other = (Funcionalidade) obj;
		if (codigoFuncionalidade == null) {
			if (other.codigoFuncionalidade != null)
				return false;
		} else if (!codigoFuncionalidade.equals(other.codigoFuncionalidade))
			return false;
		if (nomeFuncionalidade == null) {
			if (other.nomeFuncionalidade != null)
				return false;
		} else if (!nomeFuncionalidade.equals(other.nomeFuncionalidade))
			return false;
		return true;
	}
	
}
