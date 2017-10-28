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
@Table(name = "tb_produto", schema = "loja")
@Dependent
public class Produto implements EntidadeBase, Serializable {
	
	private static final long serialVersionUID = 1L;

	public Produto(){}

	@Id
	@SequenceGenerator(name="sq_idproduto", sequenceName="loja.sq_produto", allocationSize = 1, initialValue = 1) /*, schema = "loja"*/
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sq_idproduto")
	@Column(name ="id_produto", nullable = false)
	private Long codigoProduto;
	
	@Column(name = "nm_produto", nullable = false)
	private String nomeProduto;
	
	@ManyToOne
	@JoinColumn(name = "id_sub_categoria")
	private SubCategoria subCategoria;
	
	@Column(name = "qt_produto", nullable = false)
	private Long quantidadeProduto;
	
	@Column(name = "vl_produto", nullable = false)
	private Long valorProduto;
	
	@Column(name = "nu_codigo_barra")
	private String codigoBarra;
	
	@Column(name = "im_produto")
	private Byte imagemProduto;
	
	@Column(name = "dt_produto")
	private Date dataProduto;
	
	
	@Override
	public Long getId() {
		return codigoProduto;
	}


	public Long getCodigoProduto() {
		return codigoProduto;
	}


	public void setCodigoProduto(Long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}


	public String getNomeProduto() {
		return nomeProduto;
	}


	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}


	public SubCategoria getSubCategoria() {
		return subCategoria;
	}


	public void setSubCategoria(SubCategoria subCategoria) {
		this.subCategoria = subCategoria;
	}


	public Long getQuantidadeProduto() {
		return quantidadeProduto;
	}


	public void setQuantidadeProduto(Long quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}


	public Long getValorProduto() {
		return valorProduto;
	}


	public void setValorProduto(Long valorProduto) {
		this.valorProduto = valorProduto;
	}


	public String getCodigoBarra() {
		return codigoBarra;
	}


	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}


	public Byte getImagemProduto() {
		return imagemProduto;
	}


	public void setImagemProduto(Byte imagemProduto) {
		this.imagemProduto = imagemProduto;
	}


	public Date getDataProduto() {
		return dataProduto;
	}


	public void setDataProduto(Date dataProduto) {
		this.dataProduto = dataProduto;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoBarra == null) ? 0 : codigoBarra.hashCode());
		result = prime * result
				+ ((codigoProduto == null) ? 0 : codigoProduto.hashCode());
		result = prime * result
				+ ((dataProduto == null) ? 0 : dataProduto.hashCode());
		result = prime * result
				+ ((imagemProduto == null) ? 0 : imagemProduto.hashCode());
		result = prime * result
				+ ((nomeProduto == null) ? 0 : nomeProduto.hashCode());
		result = prime
				* result
				+ ((quantidadeProduto == null) ? 0 : quantidadeProduto
						.hashCode());
		result = prime * result
				+ ((subCategoria == null) ? 0 : subCategoria.hashCode());
		result = prime * result
				+ ((valorProduto == null) ? 0 : valorProduto.hashCode());
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
		Produto other = (Produto) obj;
		if (codigoBarra == null) {
			if (other.codigoBarra != null)
				return false;
		} else if (!codigoBarra.equals(other.codigoBarra))
			return false;
		if (codigoProduto == null) {
			if (other.codigoProduto != null)
				return false;
		} else if (!codigoProduto.equals(other.codigoProduto))
			return false;
		if (dataProduto == null) {
			if (other.dataProduto != null)
				return false;
		} else if (!dataProduto.equals(other.dataProduto))
			return false;
		if (imagemProduto == null) {
			if (other.imagemProduto != null)
				return false;
		} else if (!imagemProduto.equals(other.imagemProduto))
			return false;
		if (nomeProduto == null) {
			if (other.nomeProduto != null)
				return false;
		} else if (!nomeProduto.equals(other.nomeProduto))
			return false;
		if (quantidadeProduto == null) {
			if (other.quantidadeProduto != null)
				return false;
		} else if (!quantidadeProduto.equals(other.quantidadeProduto))
			return false;
		if (subCategoria == null) {
			if (other.subCategoria != null)
				return false;
		} else if (!subCategoria.equals(other.subCategoria))
			return false;
		if (valorProduto == null) {
			if (other.valorProduto != null)
				return false;
		} else if (!valorProduto.equals(other.valorProduto))
			return false;
		return true;
	}	
	
}
