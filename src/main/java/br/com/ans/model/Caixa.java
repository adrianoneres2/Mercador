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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tb_caixa", schema="loja")
@Dependent
public class Caixa implements EntidadeBase, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Caixa(){}
	
	@Id
	@SequenceGenerator(name="sq_idcaixa", sequenceName="loja.sq_idcaixa", allocationSize = 1, initialValue = 1) /*, schema = "loja"*/
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sq_idcaixa")
	@Column(name="id_caixa", nullable=false)
	private Long codigoCaixa;
	
	@Column(name="dt_abertura", nullable=false)
	private Date dataAbertura;

	@Column(name="dt_fechamento", nullable=false)
	private Date dataFechamento;

	@OneToOne
	@JoinColumn(name = "id_usuario_operador")
	private Usuario usuarioOperador;

	@OneToOne
	@JoinColumn(name = "id_usuario_abertura")
	private Usuario usuarioAbertura;

	@OneToOne
	@JoinColumn(name = "id_usuario_fechamento")
	private Usuario usuarioFechamento;
	
	public Long getCodigoCaixa() {
		return codigoCaixa;
	}



	public void setCodigoCaixa(Long codigoCaixa) {
		this.codigoCaixa = codigoCaixa;
	}



	public Date getDataAbertura() {
		return dataAbertura;
	}



	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}



	public Date getDataFechamento() {
		return dataFechamento;
	}



	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}



	public Usuario getUsuarioOperador() {
		return usuarioOperador;
	}



	public void setUsuarioOperador(Usuario usuarioOperador) {
		this.usuarioOperador = usuarioOperador;
	}



	public Usuario getUsuarioAbertura() {
		return usuarioAbertura;
	}



	public void setUsuarioAbertura(Usuario usuarioAbertura) {
		this.usuarioAbertura = usuarioAbertura;
	}



	public Usuario getUsuarioFechamento() {
		return usuarioFechamento;
	}



	public void setUsuarioFechamento(Usuario usuarioFechamento) {
		this.usuarioFechamento = usuarioFechamento;
	}

	@Override
	public Long getId() {
		return codigoCaixa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoCaixa == null) ? 0 : codigoCaixa.hashCode());
		result = prime * result
				+ ((dataAbertura == null) ? 0 : dataAbertura.hashCode());
		result = prime * result
				+ ((dataFechamento == null) ? 0 : dataFechamento.hashCode());
		result = prime * result
				+ ((usuarioAbertura == null) ? 0 : usuarioAbertura.hashCode());
		result = prime
				* result
				+ ((usuarioFechamento == null) ? 0 : usuarioFechamento
						.hashCode());
		result = prime * result
				+ ((usuarioOperador == null) ? 0 : usuarioOperador.hashCode());
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
		Caixa other = (Caixa) obj;
		if (codigoCaixa == null) {
			if (other.codigoCaixa != null)
				return false;
		} else if (!codigoCaixa.equals(other.codigoCaixa))
			return false;
		if (dataAbertura == null) {
			if (other.dataAbertura != null)
				return false;
		} else if (!dataAbertura.equals(other.dataAbertura))
			return false;
		if (dataFechamento == null) {
			if (other.dataFechamento != null)
				return false;
		} else if (!dataFechamento.equals(other.dataFechamento))
			return false;
		if (usuarioAbertura == null) {
			if (other.usuarioAbertura != null)
				return false;
		} else if (!usuarioAbertura.equals(other.usuarioAbertura))
			return false;
		if (usuarioFechamento == null) {
			if (other.usuarioFechamento != null)
				return false;
		} else if (!usuarioFechamento.equals(other.usuarioFechamento))
			return false;
		if (usuarioOperador == null) {
			if (other.usuarioOperador != null)
				return false;
		} else if (!usuarioOperador.equals(other.usuarioOperador))
			return false;
		return true;
	}
}
