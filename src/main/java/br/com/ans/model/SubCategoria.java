package br.com.ans.model;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tp_sub_categoria", schema = "loja")
@Dependent
public class SubCategoria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_sub_categoria", nullable = false)
	private Long codigoSubCategoria; 
	
	@Column(name = "ds_sub_categoria", nullable = false)
	private String descricaoSubCategoria;
	
	@Inject
	private Categoria categoria; 
	
	@Inject
	private Genero genero;

	public Long getCodigoSubCategoria() {
		return codigoSubCategoria;
	}

	public void setCodigoSubCategoria(Long codigoSubCategoria) {
		this.codigoSubCategoria = codigoSubCategoria;
	}

	public String getDescricaoSubCategoria() {
		return descricaoSubCategoria;
	}

	public void setDescricaoSubCategoria(String descricaoSubCategoria) {
		this.descricaoSubCategoria = descricaoSubCategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoria == null) ? 0 : categoria.hashCode());
		result = prime
				* result
				+ ((codigoSubCategoria == null) ? 0 : codigoSubCategoria
						.hashCode());
		result = prime
				* result
				+ ((descricaoSubCategoria == null) ? 0 : descricaoSubCategoria
						.hashCode());
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
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
		SubCategoria other = (SubCategoria) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (codigoSubCategoria == null) {
			if (other.codigoSubCategoria != null)
				return false;
		} else if (!codigoSubCategoria.equals(other.codigoSubCategoria))
			return false;
		if (descricaoSubCategoria == null) {
			if (other.descricaoSubCategoria != null)
				return false;
		} else if (!descricaoSubCategoria.equals(other.descricaoSubCategoria))
			return false;
		if (genero == null) {
			if (other.genero != null)
				return false;
		} else if (!genero.equals(other.genero))
			return false;
		return true;
	}

	
	
}
