package br.com.ans.model;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tp_tipo_venda", schema = "loja")
@Dependent
public class TipoVenda implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_tipo_venda", nullable = false)
	private Long codigoTipoVenda;
	
	@Column(name = "nm_tipo_venda", nullable = false)
	private String nomeTipoVenda;

	public Long getCodigoTipoVenda() {
		return codigoTipoVenda;
	}

	public void setCodigoTipoVenda(Long codigoTipoVenda) {
		this.codigoTipoVenda = codigoTipoVenda;
	}

	public String getNomeTipoVenda() {
		return nomeTipoVenda;
	}

	public void setNomeTipoVenda(String nomeTipoVenda) {
		this.nomeTipoVenda = nomeTipoVenda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoTipoVenda == null) ? 0 : codigoTipoVenda.hashCode());
		result = prime * result
				+ ((nomeTipoVenda == null) ? 0 : nomeTipoVenda.hashCode());
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
		TipoVenda other = (TipoVenda) obj;
		if (codigoTipoVenda == null) {
			if (other.codigoTipoVenda != null)
				return false;
		} else if (!codigoTipoVenda.equals(other.codigoTipoVenda))
			return false;
		if (nomeTipoVenda == null) {
			if (other.nomeTipoVenda != null)
				return false;
		} else if (!nomeTipoVenda.equals(other.nomeTipoVenda))
			return false;
		return true;
	}
	
}
