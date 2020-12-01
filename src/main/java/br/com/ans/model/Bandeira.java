package br.com.ans.model;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tp_bandeira", schema = "loja" )
@Dependent
public class Bandeira implements Serializable {
	private static final long serialVersionUID = 1l;

	@Id
	@Column(name = "id_bandeira", nullable = false)
	private Long codigoBandeira;
	
	@Column(name = "nm_bandeira", nullable = false)
	private String nomeBandeira;

	public Long getCodigoBandeira() {
		return codigoBandeira;
	}

	public void setCodigoBandeira(Long codigoBandeira) {
		this.codigoBandeira = codigoBandeira;
	}

	public String getNomeBandeira() {
		return nomeBandeira;
	}

	public void setNomeBandeira(String nomeBandeira) {
		this.nomeBandeira = nomeBandeira;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoBandeira == null) ? 0 : codigoBandeira.hashCode());
		result = prime * result + ((nomeBandeira == null) ? 0 : nomeBandeira.hashCode());
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
		Bandeira other = (Bandeira) obj;
		if (codigoBandeira == null) {
			if (other.codigoBandeira != null)
				return false;
		} else if (!codigoBandeira.equals(other.codigoBandeira))
			return false;
		if (nomeBandeira == null) {
			if (other.nomeBandeira != null)
				return false;
		} else if (!nomeBandeira.equals(other.nomeBandeira))
			return false;
		return true;
	}
	
}
