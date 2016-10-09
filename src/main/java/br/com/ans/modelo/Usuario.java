package br.com.ans.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_usuario", schema = "loja")
public class Usuario implements EntidadeBase{
	
		@Id
		@SequenceGenerator(name="sq_idusuario", sequenceName="loja.sq_idusuario", allocationSize = 1, initialValue = 1) /*, schema = "loja"*/
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sq_idusuario")
		@Column(name = "id_usuario", nullable = false)
		private Long  codigoUsuario;
		
		@Column(name = "nm_usuario", nullable = false)
		private String   nomeUsuario;
		
		@Column(name = "nm_senha", nullable = false)
		private String   senha;
		
		@Column(name = "dt_cadastro", nullable = true)
		@Transient
		private Date dataCadastro;
		
		@Column(name = "ds_complemento")
		private String   complemento;
		
		@Column(name = "ds_referencia")
		private String   referencia;
		
		@Column(name = "nr_telefone_fixo")
		private String   telefoneFixo;
		
		@Column(name = "nr_telefone_celular", nullable = false)
		private String   telefoneCelular;
		
		@Column(name = "nm_sexo", nullable = false)
		private String   sexo;
		
		@Column(name = "nr_rg", nullable = false)
		private Integer  rg;
		
		@Column(name = "dt_nascimento", nullable = false)
		private Date dataNascimento;
		
		@Column(name = "nm_email", nullable = false)
		private String   email;
		
		@Column(name = "st_usuario_ativo", nullable = false)
		@Transient
		private String   usuarioAtivo;
		
		@Column(name = "id_usuario_cadastro	", nullable = false)
		private Long  codigoUsuarioCadastro;
		
		public Long getCodigoUsuario() {
			return codigoUsuario;
		}
		public void setCodigoUsuario(Long codigoUsuario) {
			this.codigoUsuario = codigoUsuario;
		}
		public String getNomeUsuario() {
			return nomeUsuario;
		}
		public void setNomeUsuario(String nomeUsuario) {
			this.nomeUsuario = nomeUsuario;
		}
		public String getSenha() {
			return senha;
		}
		public void setSenha(String senha) {
			this.senha = senha;
		}
		public Date getDataCadastro() {
			return dataCadastro;
		}
		public void setDataCadastro(Date dataCadastro) {
			this.dataCadastro = dataCadastro;
		}
		public String getComplemento() {
			return complemento;
		}
		public void setComplemento(String complemento) {
			this.complemento = complemento;
		}
		public String getReferencia() {
			return referencia;
		}
		public void setReferencia(String ferencia) {
			this.referencia = ferencia;
		}
		public String getTelefoneFixo() {
			return telefoneFixo;
		}
		public void setTelefoneFixo(String telefoneFixo) {
			this.telefoneFixo = telefoneFixo;
		}
		public String getTelefoneCelular() {
			return telefoneCelular;
		}
		public void setTelefoneCelular(String telefoneCelular) {
			this.telefoneCelular = telefoneCelular;
		}
		public String getSexo() {
			return sexo;
		}
		public void setSexo(String sexo) {
			this.sexo = sexo;
		}
		public Integer getRg() {
			return rg;
		}
		public void setRg(Integer rg) {
			this.rg = rg;
		}
		public Date getDataNascimento() {
			return dataNascimento;
		}
		public void setDataNascimento(Date dataNascimento) {
			this.dataNascimento = dataNascimento;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getUsuarioAtivo() {
			return usuarioAtivo;
		}
		public void setUsuarioAtivo(String usuarioAtivo) {
			this.usuarioAtivo = usuarioAtivo;
		}
		
		public Long getId() {
			return codigoUsuario;
		}		
		
		public Long getCodigoUsuarioCadastro() {
			return codigoUsuarioCadastro;
		}
		public void setCodigoUsuarioCadastro(Long codigoUsuarioCadastro) {
			this.codigoUsuarioCadastro = codigoUsuarioCadastro;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((codigoUsuario == null) ? 0 : codigoUsuario.hashCode());
			result = prime * result
					+ ((nomeUsuario == null) ? 0 : nomeUsuario.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Usuario other = (Usuario) obj;
			if (codigoUsuario == null) {
				if (other.codigoUsuario != null)
					return false;
			} else if (!codigoUsuario.equals(other.codigoUsuario))
				return false;
			if (nomeUsuario == null) {
				if (other.nomeUsuario != null)
					return false;
			} else if (!nomeUsuario.equals(other.nomeUsuario))
				return false;
			return true;
		}		
		
}
