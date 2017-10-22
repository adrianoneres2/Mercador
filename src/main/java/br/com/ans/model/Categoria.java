package br.com.ans.model;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tp_categoria", schema = "loja")
@Dependent
public class Categoria implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_categoria", nullable = false)
	private Long codigoCategoria;
	
	@Column(name = "ds_categoria", nullable = false)
	private String descricaoCategoria;

	public Long getCodigoCategoria() {
		return codigoCategoria;
	}

	public void setCodigoCategoria(Long codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public void setDescricaoCategoria(String nomeCategoria) {
		this.descricaoCategoria = nomeCategoria;
	}
	
	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoCategoria == null) ? 0 : codigoCategoria.hashCode());
		result = prime
				* result
				+ ((descricaoCategoria == null) ? 0 : descricaoCategoria
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
		Categoria other = (Categoria) obj;
		if (codigoCategoria == null) {
			if (other.codigoCategoria != null)
				return false;
		} else if (!codigoCategoria.equals(other.codigoCategoria))
			return false;
		if (descricaoCategoria == null) {
			if (other.descricaoCategoria != null)
				return false;
		} else if (!descricaoCategoria.equals(other.descricaoCategoria))
			return false;
		return true;
	}

	
}
