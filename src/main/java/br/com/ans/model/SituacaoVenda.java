package br.com.ans.model;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tp_situacao_venda", schema = "loja")
@Dependent
public class SituacaoVenda implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_situacao_venda", nullable = false)
	private Long codigoSituacaoVenda;
	
	@Column(name = "nm_situacao_venda", nullable = false)
	private String nomeSituacaoVenda;

	public Long getCodigoSituacaoVenda() {
		return codigoSituacaoVenda;
	}

	public void setCodigoSituacaoVenda(Long codigoSituacaoVenda) {
		this.codigoSituacaoVenda = codigoSituacaoVenda;
	}

	public String getNomeSituacaoVenda() {
		return nomeSituacaoVenda;
	}

	public void setNomeSituacaoVenda(String nomeSituacaoVenda) {
		this.nomeSituacaoVenda = nomeSituacaoVenda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codigoSituacaoVenda == null) ? 0 : codigoSituacaoVenda
						.hashCode());
		result = prime
				* result
				+ ((nomeSituacaoVenda == null) ? 0 : nomeSituacaoVenda
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
		SituacaoVenda other = (SituacaoVenda) obj;
		if (codigoSituacaoVenda == null) {
			if (other.codigoSituacaoVenda != null)
				return false;
		} else if (!codigoSituacaoVenda.equals(other.codigoSituacaoVenda))
			return false;
		if (nomeSituacaoVenda == null) {
			if (other.nomeSituacaoVenda != null)
				return false;
		} else if (!nomeSituacaoVenda.equals(other.nomeSituacaoVenda))
			return false;
		return true;
	}
	
}
