package br.com.ans.model;

import java.io.Serializable;

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
import javax.persistence.Transient;

@Entity
@Table(name = "ta_item_venda", schema = "loja")
@Dependent
public class ItemVenda implements EntidadeBase, Serializable {

	public static final long serialVersionUID = 1L;
	
	public ItemVenda(){}
	
	@Id
	@SequenceGenerator(name="sq_itemidvenda", sequenceName="loja.sq_itemidvenda", allocationSize = 1, initialValue = 1) /*, schema = "loja"*/
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sq_itemidvenda")
	@Column(name ="id_item_venda", nullable = false)
	private Long codigoItemVenda;
	
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "id_venda")
	private Venda venda;
	
	@Column(name = "qt_item")
	private Long quantidadeItem; 
	
	@Column(name = "vl_item")
	private Double valorItem;
	
	@Column(name = "vl_desconto")
	private Double valorDesconto;

	@Column(name = "nr_item")
	private Long numeroItem = 0L;
	
	@Transient
	private Double valorTotal;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_autorizacao")
	private Usuario usuarioAutorizador;
	
	@ManyToOne
	@JoinColumn(name = "id_autorizacao")
	private Autorizacao autorizacao;

	@Override
	public Long getId() {
		return codigoItemVenda;
	}

	public Long getCodigoItemVenda() {
		return codigoItemVenda;
	}

	public void setCodigoItemVenda(Long codigoItemVenda) {
		this.codigoItemVenda = codigoItemVenda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Long getQuantidadeItem() {
		return quantidadeItem;
	}

	public void setQuantidadeItem(Long quantidadeItem) {
		this.quantidadeItem = quantidadeItem;
	}

	public Double getValorItem() {
		return valorItem;
	}

	public void setValorItem(Double valorItem) {
		this.valorItem = valorItem;
	}

	public Double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Usuario getUsuarioAutorizador() {
		return usuarioAutorizador;
	}

	public void setUsuarioAutorizador(Usuario usuarioAutorizador) {
		this.usuarioAutorizador = usuarioAutorizador;
	}

	public Autorizacao getAutorizacao() {
		if(autorizacao == null) {
			return new Autorizacao();
		}
		return autorizacao;
	}

	public void setAutorizacao(Autorizacao autorizacao) {
		this.autorizacao = autorizacao;
	}

	public Double getValorTotal() {
		return getQuantidadeItem()*getValorItem();
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}	
	
	public Long getNumeroItem() {
		return numeroItem;
	}

	public void setNumeroItem(Long numeroItem) {
		this.numeroItem = numeroItem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autorizacao == null) ? 0 : autorizacao.hashCode());
		result = prime * result + ((codigoItemVenda == null) ? 0 : codigoItemVenda.hashCode());
		result = prime * result + ((numeroItem == null) ? 0 : numeroItem.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((quantidadeItem == null) ? 0 : quantidadeItem.hashCode());
		result = prime * result + ((usuarioAutorizador == null) ? 0 : usuarioAutorizador.hashCode());
		result = prime * result + ((valorDesconto == null) ? 0 : valorDesconto.hashCode());
		result = prime * result + ((valorItem == null) ? 0 : valorItem.hashCode());
		result = prime * result + ((valorTotal == null) ? 0 : valorTotal.hashCode());
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
		ItemVenda other = (ItemVenda) obj;
		if (autorizacao == null) {
			if (other.autorizacao != null)
				return false;
		} else if (!autorizacao.equals(other.autorizacao))
			return false;
		if (codigoItemVenda == null) {
			if (other.codigoItemVenda != null)
				return false;
		} else if (!codigoItemVenda.equals(other.codigoItemVenda))
			return false;
		if (numeroItem == null) {
			if (other.numeroItem != null)
				return false;
		} else if (!numeroItem.equals(other.numeroItem))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (quantidadeItem == null) {
			if (other.quantidadeItem != null)
				return false;
		} else if (!quantidadeItem.equals(other.quantidadeItem))
			return false;
		if (usuarioAutorizador == null) {
			if (other.usuarioAutorizador != null)
				return false;
		} else if (!usuarioAutorizador.equals(other.usuarioAutorizador))
			return false;
		if (valorDesconto == null) {
			if (other.valorDesconto != null)
				return false;
		} else if (!valorDesconto.equals(other.valorDesconto))
			return false;
		if (valorItem == null) {
			if (other.valorItem != null)
				return false;
		} else if (!valorItem.equals(other.valorItem))
			return false;
		if (valorTotal == null) {
			if (other.valorTotal != null)
				return false;
		} else if (!valorTotal.equals(other.valorTotal))
			return false;
		if (venda == null) {
			if (other.venda != null)
				return false;
		} else if (!venda.equals(other.venda))
			return false;
		return true;
	}
}
