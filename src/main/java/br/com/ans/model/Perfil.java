package br.com.ans.model;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_perfil", schema="loja")
@Dependent
public class Perfil implements EntidadeBase, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Perfil(){}
	
	@Id
	@Column(name="id_perfil", nullable=false)
	private Long codigoPerfil;
	
	@Column(name="nm_perfil", nullable=false)
	private String nomePerfil;
	
	@OneToMany(mappedBy = "perfil")
	///@JoinColumn(name = "id_perfil")
	private List<PerfilFuncionalidade> perfilFuncionalidade;
	
	public Long getCodigoPerfil() {
		return codigoPerfil;
	}
	public void setCodigoPerfil(Long codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}
	public String getNomePerfil() {
		return nomePerfil;
	}
	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}
	
	@Override
	public Long getId() {
		return codigoPerfil;
	}
	
	public List<PerfilFuncionalidade> getPerfilFuncionalidade() {
		return perfilFuncionalidade;
	}
	public void setPerfilFuncionalidade(
			List<PerfilFuncionalidade> perfilFuncionalidade) {
		this.perfilFuncionalidade = perfilFuncionalidade;
	}
	// Método responsavel por retornar o código como string para o converter omnifaces.SelectItemsConverter
	@Override
	public String toString() {
	    return String.format("%s[codigoPerfil=%d]", getClass().getSimpleName(), getCodigoPerfil());
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoPerfil == null) ? 0 : codigoPerfil.hashCode());
		result = prime * result
				+ ((nomePerfil == null) ? 0 : nomePerfil.hashCode());
		result = prime
				* result
				+ ((perfilFuncionalidade == null) ? 0 : perfilFuncionalidade
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
		Perfil other = (Perfil) obj;
		if (codigoPerfil == null) {
			if (other.codigoPerfil != null)
				return false;
		} else if (!codigoPerfil.equals(other.codigoPerfil))
			return false;
		if (nomePerfil == null) {
			if (other.nomePerfil != null)
				return false;
		} else if (!nomePerfil.equals(other.nomePerfil))
			return false;
		if (perfilFuncionalidade == null) {
			if (other.perfilFuncionalidade != null)
				return false;
		} else if (!perfilFuncionalidade.equals(other.perfilFuncionalidade))
			return false;
		return true;
	}
	
}
