package br.com.ans.model;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ta_venda_forma_pagamento", schema = "loja")
@Dependent
public class VendaFormaPagamento implements EntidadeBase, Serializable {
	
	private static final long serialVersionUID = 1L;

	public VendaFormaPagamento(){}
	
	@Id
	@SequenceGenerator(name="sq_idvendaformapagamento", sequenceName="loja.sq_idvendaformapagamento", allocationSize = 1, initialValue = 1) /*, schema = "loja"*/
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sq_idvendaformapagamento")
	@Column(name = "id_venda_forma_pagamento", nullable = false)
	private Long codigoVendaFormaPagamento;
	
	@ManyToOne
	@JoinColumn(name = "id_venda")
	private Venda venda;
	
	@ManyToOne
	@JoinColumn(name = "id_forma_pagamento")
	private FormaPagamento formaPagamento;

	@ManyToOne
	@JoinColumn(name = "id_bandeira")
	private Bandeira bandeira;
	
	@Column(name = "vl_parcela")
	private Double valorParcela;
	
	@Column(name = "nr_parcela")
	private Long numeroParcela;
	
	@Column(name = "dt_parcela")
	private Date dataParcela;

	public Long getCodigoVendaFormaPagamento() {
		return codigoVendaFormaPagamento;
	}
	
	@Override
	public Long getId() {
		return codigoVendaFormaPagamento;
	}

	public void setCodigoVendaFormaPagamento(Long codigoVendaFormaPagamento) {
		this.codigoVendaFormaPagamento = codigoVendaFormaPagamento;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Bandeira getBandeira() {
		return bandeira;
	}

	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira;
	}

	public Double getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(Double valorParcela) {
		this.valorParcela = valorParcela;
	}

	public Long getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Long numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public Date getDataParcela() {
		return dataParcela;
	}

	public void setDataParcela(Date dataParcela) {
		this.dataParcela = dataParcela;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bandeira == null) ? 0 : bandeira.hashCode());
		result = prime * result + ((codigoVendaFormaPagamento == null) ? 0 : codigoVendaFormaPagamento.hashCode());
		result = prime * result + ((dataParcela == null) ? 0 : dataParcela.hashCode());
		result = prime * result + ((formaPagamento == null) ? 0 : formaPagamento.hashCode());
		result = prime * result + ((numeroParcela == null) ? 0 : numeroParcela.hashCode());
		result = prime * result + ((valorParcela == null) ? 0 : valorParcela.hashCode());
		result = prime * result + ((venda == null) ? 0 : venda.hashCode());
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
		VendaFormaPagamento other = (VendaFormaPagamento) obj;
		if (bandeira == null) {
			if (other.bandeira != null)
				return false;
		} else if (!bandeira.equals(other.bandeira))
			return false;
		if (codigoVendaFormaPagamento == null) {
			if (other.codigoVendaFormaPagamento != null)
				return false;
		} else if (!codigoVendaFormaPagamento.equals(other.codigoVendaFormaPagamento))
			return false;
		if (dataParcela == null) {
			if (other.dataParcela != null)
				return false;
		} else if (!dataParcela.equals(other.dataParcela))
			return false;
		if (formaPagamento == null) {
			if (other.formaPagamento != null)
				return false;
		} else if (!formaPagamento.equals(other.formaPagamento))
			return false;
		if (numeroParcela == null) {
			if (other.numeroParcela != null)
				return false;
		} else if (!numeroParcela.equals(other.numeroParcela))
			return false;
		if (valorParcela == null) {
			if (other.valorParcela != null)
				return false;
		} else if (!valorParcela.equals(other.valorParcela))
			return false;
		if (venda == null) {
			if (other.venda != null)
				return false;
		} else if (!venda.equals(other.venda))
			return false;
		return true;
	}
	
	
}
