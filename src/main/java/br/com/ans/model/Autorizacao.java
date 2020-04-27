package br.com.ans.model;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tp_autorizacao", schema = "loja")
@Dependent
public class Autorizacao  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_autorizacao", nullable = false)
	private Long codigoAutorizacao;
	
	@Column(name = "nm_autorizacao", nullable = false)
	private String nomeAutorizacao;

	public Long getCodigoAutorizacao() {
		return codigoAutorizacao;
	}

	public void setCodigoAutorizacao(Long codigoAutorizacao) {
		this.codigoAutorizacao = codigoAutorizacao;
	}

	public String getNomeAutorizacao() {
		return nomeAutorizacao;
	}

	public void setNomeAutorizacao(String nomeAutorizacao) {
		this.nomeAutorizacao = nomeAutorizacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codigoAutorizacao == null) ? 0 : codigoAutorizacao
						.hashCode());
		result = prime * result
				+ ((nomeAutorizacao == null) ? 0 : nomeAutorizacao.hashCode());
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
		Autorizacao other = (Autorizacao) obj;
		if (codigoAutorizacao == null) {
			if (other.codigoAutorizacao != null)
				return false;
		} else if (!codigoAutorizacao.equals(other.codigoAutorizacao))
			return false;
		if (nomeAutorizacao == null) {
			if (other.nomeAutorizacao != null)
				return false;
		} else if (!nomeAutorizacao.equals(other.nomeAutorizacao))
			return false;
		return true;
	}
}
