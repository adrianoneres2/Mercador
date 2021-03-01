package br.com.ans.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	@JoinColumn(name = "id_usuario_cliente")
	private Usuario usuarioCliente;

	@ManyToOne
	@JoinColumn(name = "id_tipo_venda")
	private TipoVenda tipoVenda;

	@ManyToOne
	@JoinColumn(name = "id_situacao_venda")
	private SituacaoVenda situacaoVenda;
	
	@ManyToOne
	@JoinColumn(name = "id_caixa")
	private Caixa caixa;
	
	@OneToMany(mappedBy="venda")
	private List<ItemVenda> listaItemVenda = new ArrayList<ItemVenda>();

	@OneToMany(mappedBy="venda")
	private List<VendaFormaPagamento> listaVendaFormaPagamento = new ArrayList<VendaFormaPagamento>();
	
	@ManyToOne
	@JoinColumn(name = "id_venda_orcamento")
	private Venda vendaOrcamento;	
	
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

	public Usuario getUsuarioCliente() {
		return usuarioCliente;
	}

	public void setUsuarioCliente(Usuario usuarioCliente) {
		this.usuarioCliente = usuarioCliente;
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
		return vendaOrcamento;
	}

	public void setVendaOrcamento(Venda vendaOrcamento) {
		this.vendaOrcamento = vendaOrcamento;
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
	
	public List<ItemVenda> getItemVenda() {
		return listaItemVenda;
	}

	public void setItemVenda(List<ItemVenda> itemVenda) {
		this.listaItemVenda = itemVenda;
	}

	public List<ItemVenda> getListaItemVenda() {
		return listaItemVenda;
	}

	public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
		this.listaItemVenda = listaItemVenda;
	}

	public List<VendaFormaPagamento> getListaVendaFormaPagamento() {
		return listaVendaFormaPagamento;
	}

	public void setListaVendaFormaPagamento(List<VendaFormaPagamento> listaVendaFormaPagamento) {
		this.listaVendaFormaPagamento = listaVendaFormaPagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caixa == null) ? 0 : caixa.hashCode());
		result = prime * result + ((codigoVenda == null) ? 0 : codigoVenda.hashCode());
		result = prime * result + ((dataVenda == null) ? 0 : dataVenda.hashCode());
		result = prime * result + ((dataVendaFinalizada == null) ? 0 : dataVendaFinalizada.hashCode());
		result = prime * result + ((listaItemVenda == null) ? 0 : listaItemVenda.hashCode());
		result = prime * result + ((listaVendaFormaPagamento == null) ? 0 : listaVendaFormaPagamento.hashCode());
		result = prime * result + ((situacaoVenda == null) ? 0 : situacaoVenda.hashCode());
		result = prime * result + ((tipoVenda == null) ? 0 : tipoVenda.hashCode());
		result = prime * result + ((usuarioCliente == null) ? 0 : usuarioCliente.hashCode());
		result = prime * result + ((vendaOrcamento == null) ? 0 : vendaOrcamento.hashCode());
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
		if (listaItemVenda == null) {
			if (other.listaItemVenda != null)
				return false;
		} else if (!listaItemVenda.equals(other.listaItemVenda))
			return false;
		if (listaVendaFormaPagamento == null) {
			if (other.listaVendaFormaPagamento != null)
				return false;
		} else if (!listaVendaFormaPagamento.equals(other.listaVendaFormaPagamento))
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
		if (usuarioCliente == null) {
			if (other.usuarioCliente != null)
				return false;
		} else if (!usuarioCliente.equals(other.usuarioCliente))
			return false;
		if (vendaOrcamento == null) {
			if (other.vendaOrcamento != null)
				return false;
		} else if (!vendaOrcamento.equals(other.vendaOrcamento))
			return false;
		return true;
	}
	
}
