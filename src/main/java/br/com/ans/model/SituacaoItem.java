package br.com.ans.model;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tp_situacao_item", schema = "loja")
@Dependent
public class SituacaoItem  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_situacao_item", nullable = false)
	private Long codigoSituacaoItem;
	
	@Column(name = "nm_situacao_item", nullable = false)
	private String nomeSituacaoItem;

	public Long getCodigoSituacaoItem() {
		return codigoSituacaoItem;
	}

	public void setCodigoSituacaoItem(Long codigoSituacaoItem) {
		this.codigoSituacaoItem = codigoSituacaoItem;
	}

	public String getNomeSituacaoItem() {
		return nomeSituacaoItem;
	}

	public void setNomeSituacaoItem(String nomeSituacaoItem) {
		this.nomeSituacaoItem = nomeSituacaoItem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoSituacaoItem == null) ? 0 : codigoSituacaoItem.hashCode());
		result = prime * result + ((nomeSituacaoItem == null) ? 0 : nomeSituacaoItem.hashCode());
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
		SituacaoItem other = (SituacaoItem) obj;
		if (codigoSituacaoItem == null) {
			if (other.codigoSituacaoItem != null)
				return false;
		} else if (!codigoSituacaoItem.equals(other.codigoSituacaoItem))
			return false;
		if (nomeSituacaoItem == null) {
			if (other.nomeSituacaoItem != null)
				return false;
		} else if (!nomeSituacaoItem.equals(other.nomeSituacaoItem))
			return false;
		return true;
	}
}
