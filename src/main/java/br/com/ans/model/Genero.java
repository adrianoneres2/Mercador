package br.com.ans.model;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tp_genero", schema = "loja")
@Dependent
public class Genero implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_genero", nullable = false)
	private Long codigoGenero;
	
	@Column(name = "ds_genero", nullable = false)
	private String descricaoGenero;

	public Long getCodigoGenero() {
		return codigoGenero;
	}

	public void setCodigoGenero(Long codigoGenero) {
		this.codigoGenero = codigoGenero;
	}

	public String getDescricaoGenero() {
		return descricaoGenero;
	}

	public void setDescricaoGenero(String descricaoGenero) {
		this.descricaoGenero = descricaoGenero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoGenero == null) ? 0 : codigoGenero.hashCode());
		result = prime * result
				+ ((descricaoGenero == null) ? 0 : descricaoGenero.hashCode());
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
		Genero other = (Genero) obj;
		if (codigoGenero == null) {
			if (other.codigoGenero != null)
				return false;
		} else if (!codigoGenero.equals(other.codigoGenero))
			return false;
		if (descricaoGenero == null) {
			if (other.descricaoGenero != null)
				return false;
		} else if (!descricaoGenero.equals(other.descricaoGenero))
			return false;
		return true;
	}
	
	
}
