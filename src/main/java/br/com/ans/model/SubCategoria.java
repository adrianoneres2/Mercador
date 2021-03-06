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
@Table(name = "tp_sub_categoria", schema = "loja")
@Dependent
public class SubCategoria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_sub_categoria", nullable = false)
	private Long codigoSubCategoria; 
	
	@Column(name = "nm_sub_categoria", nullable = false)
	private String nomeSubCategoria;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	

	public Long getCodigoSubCategoria() {
		return codigoSubCategoria;
	}

	public void setCodigoSubCategoria(Long codigoSubCategoria) {
		this.codigoSubCategoria = codigoSubCategoria;
	}

	public String getNomeSubCategoria() {
		return nomeSubCategoria;
	}

	public void setNomeSubCategoria(String nomeSubCategoria) {
		this.nomeSubCategoria = nomeSubCategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
				+ ((nomeSubCategoria == null) ? 0 : nomeSubCategoria
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
		if (nomeSubCategoria == null) {
			if (other.nomeSubCategoria != null)
				return false;
		} else if (!nomeSubCategoria.equals(other.nomeSubCategoria))
			return false;
		return true;
	}

	

	
}
