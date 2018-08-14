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
	
	@Column(name = "nm_categoria", nullable = false)
	private String nomeCategoria;

	public Long getCodigoCategoria() {
		return codigoCategoria;
	}

	public void setCodigoCategoria(Long codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
	public String getNomeCategoria() {
		return nomeCategoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoCategoria == null) ? 0 : codigoCategoria.hashCode());
		result = prime
				* result
				+ ((nomeCategoria == null) ? 0 : nomeCategoria
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
		if (nomeCategoria == null) {
			if (other.nomeCategoria != null)
				return false;
		} else if (!nomeCategoria.equals(other.nomeCategoria))
			return false;
		return true;
	}

	
}
