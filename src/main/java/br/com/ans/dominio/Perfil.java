package br.com.ans.dominio;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_perfil", schema="loja")
@RequestScoped
public class Perfil {
	
	@Id
	@Column(name="id_perfil", nullable=false)
	private Long codigoPerfil;
	
	@Column(name="nm_perfil", nullable=false)
	private String nomePerfil;
	
	public Long getCodigoPerfil() {
		return codigoPerfil;
	}
	public void setCodigoPerfil(Long codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}
	public String getNomePerfil() {
		return nomePerfil;
	}
	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}	

}
