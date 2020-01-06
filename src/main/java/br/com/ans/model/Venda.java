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
@Table(name = "tb_venda", schema = "loja")
@Dependent
public class Venda implements EntidadeBase, Serializable  {
	
	private static final long serialVersionUID = 1L;

	public Venda(){}

	@Id
	@SequenceGenerator(name="sq_idvenda", sequenceName="loja.sq_idvenda", allocationSize = 1, initialValue = 1) /*, schema = "loja"*/
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sq_idvenda")
	@Column(name ="id_venda", nullable = false)
	private Long codigoVenda;
	
	@ManyToOne
	@JoinColumn(name = "id_forma_pagamento")
	private FormaPagamento formaPagamento;

	@ManyToOne
	@JoinColumn(name = "id_usuario_cliente")
	private Usuario UsuarioCliente;

	@ManyToOne
	@JoinColumn(name = "id_tipo_venda")
	private TipoVenda tipoVenda;

	@ManyToOne
	@JoinColumn(name = "id_situacao_venda")
	private SituacaoVenda situacaoVenda;
	
	@ManyToOne
	@JoinColumn(name = "id_caixa")
	private Caixa caixa;
	
	@ManyToOne
	@JoinColumn(name = "id_venda_orcamento")
	private Venda VendaOrcamento;	
	
	@Column(name = "dt_venda")
	private Date dataVenda;

	@Column(name = "dt_venda_finalizada")
	private Date dataVendaFinalizada;
	
	@Override
	public Long getId() {
		return codigoVenda;
	}

	public Long getCodigoVenda() {
		return codigoVenda;
	}

	public void setCodigoVenda(Long codigoVenda) {
		this.codigoVenda = codigoVenda;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Usuario getUsuarioCliente() {
		return UsuarioCliente;
	}

	public void setUsuarioCliente(Usuario usuarioCliente) {
		UsuarioCliente = usuarioCliente;
	}

	public TipoVenda getTipoVenda() {
		return tipoVenda;
	}

	public void setTipoVenda(TipoVenda tipoVenda) {
		this.tipoVenda = tipoVenda;
	}

	public SituacaoVenda getSituacaoVenda() {
		return situacaoVenda;
	}

	public void setSituacaoVenda(SituacaoVenda situacaoVenda) {
		this.situacaoVenda = situacaoVenda;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public Venda getVendaOrcamento() {
		return VendaOrcamento;
	}

	public void setVendaOrcamento(Venda vendaOrcamento) {
		VendaOrcamento = vendaOrcamento;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Date getDataVendaFinalizada() {
		return dataVendaFinalizada;
	}

	public void setDataVendaFinalizada(Date dataVendaFinalizada) {
		this.dataVendaFinalizada = dataVendaFinalizada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((UsuarioCliente == null) ? 0 : UsuarioCliente.hashCode());
		result = prime * result
				+ ((VendaOrcamento == null) ? 0 : VendaOrcamento.hashCode());
		result = prime * result + ((caixa == null) ? 0 : caixa.hashCode());
		result = prime * result
				+ ((codigoVenda == null) ? 0 : codigoVenda.hashCode());
		result = prime * result
				+ ((dataVenda == null) ? 0 : dataVenda.hashCode());
		result = prime
				* result
				+ ((dataVendaFinalizada == null) ? 0 : dataVendaFinalizada
						.hashCode());
		result = prime * result
				+ ((formaPagamento == null) ? 0 : formaPagamento.hashCode());
		result = prime * result
				+ ((situacaoVenda == null) ? 0 : situacaoVenda.hashCode());
		result = prime * result
				+ ((tipoVenda == null) ? 0 : tipoVenda.hashCode());
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
		Venda other = (Venda) obj;
		if (UsuarioCliente == null) {
			if (other.UsuarioCliente != null)
				return false;
		} else if (!UsuarioCliente.equals(other.UsuarioCliente))
			return false;
		if (VendaOrcamento == null) {
			if (other.VendaOrcamento != null)
				return false;
		} else if (!VendaOrcamento.equals(other.VendaOrcamento))
			return false;
		if (caixa == null) {
			if (other.caixa != null)
				return false;
		} else if (!caixa.equals(other.caixa))
			return false;
		if (codigoVenda == null) {
			if (other.codigoVenda != null)
				return false;
		} else if (!codigoVenda.equals(other.codigoVenda))
			return false;
		if (dataVenda == null) {
			if (other.dataVenda != null)
				return false;
		} else if (!dataVenda.equals(other.dataVenda))
			return false;
		if (dataVendaFinalizada == null) {
			if (other.dataVendaFinalizada != null)
				return false;
		} else if (!dataVendaFinalizada.equals(other.dataVendaFinalizada))
			return false;
		if (formaPagamento == null) {
			if (other.formaPagamento != null)
				return false;
		} else if (!formaPagamento.equals(other.formaPagamento))
			return false;
		if (situacaoVenda == null) {
			if (other.situacaoVenda != null)
				return false;
		} else if (!situacaoVenda.equals(other.situacaoVenda))
			return false;
		if (tipoVenda == null) {
			if (other.tipoVenda != null)
				return false;
		} else if (!tipoVenda.equals(other.tipoVenda))
			return false;
		return true;
	}

	
}
