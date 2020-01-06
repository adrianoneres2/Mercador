package br.com.ans.model;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tp_forma_pagamento", schema = "loja")
@NamedQueries(
		@NamedQuery(name = "FormaPagamento.todas", query = "SELECT fp FROM FormaPagamento fp"))
@Dependent
public class FormaPagamento implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_forma_pagamento", nullable = false)
	private Long codigoFormaPagamento;
	
	@Column(name = "nm_forma_pagamento", nullable = false)
	private String nomeFormaPagamento;

	public Long getCodigoFormaPagamento() {
		return codigoFormaPagamento;
	}

	public void setCodigoFormaPagamento(Long codigoFormaPagamento) {
		this.codigoFormaPagamento = codigoFormaPagamento;
	}

	public String getNomeFormaPagamento() {
		return nomeFormaPagamento;
	}

	public void setNomeFormaPagamento(String nomeFormaPagamento) {
		this.nomeFormaPagamento = nomeFormaPagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codigoFormaPagamento == null) ? 0 : codigoFormaPagamento
						.hashCode());
		result = prime
				* result
				+ ((nomeFormaPagamento == null) ? 0 : nomeFormaPagamento
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
		FormaPagamento other = (FormaPagamento) obj;
		if (codigoFormaPagamento == null) {
			if (other.codigoFormaPagamento != null)
				return false;
		} else if (!codigoFormaPagamento.equals(other.codigoFormaPagamento))
			return false;
		if (nomeFormaPagamento == null) {
			if (other.nomeFormaPagamento != null)
				return false;
		} else if (!nomeFormaPagamento.equals(other.nomeFormaPagamento))
			return false;
		return true;
	}
	
}
