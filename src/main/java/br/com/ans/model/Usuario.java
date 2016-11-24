package br.com.ans.model;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
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
@RequestScoped
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
		
		@Column(name = "id_perfil", nullable = false)
		private Long codigoPerfil;
		
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
				
		public Long getCodigoPerfil() {
			return codigoPerfil;
		}
		public void setCodigoPerfil(Long codigoPerfil) {
			this.codigoPerfil = codigoPerfil;
		}
		
		@Override
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
			result = prime * result + ((codigoUsuario == null) ? 0 : codigoUsuario.hashCode());
			result = prime * result + ((codigoUsuarioCadastro == null) ? 0 : codigoUsuarioCadastro.hashCode());
			result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
			result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
			result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + ((nomeUsuario == null) ? 0 : nomeUsuario.hashCode());
			result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
			result = prime * result + ((rg == null) ? 0 : rg.hashCode());
			result = prime * result + ((senha == null) ? 0 : senha.hashCode());
			result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
			result = prime * result + ((telefoneCelular == null) ? 0 : telefoneCelular.hashCode());
			result = prime * result + ((telefoneFixo == null) ? 0 : telefoneFixo.hashCode());
			result = prime * result + ((usuarioAtivo == null) ? 0 : usuarioAtivo.hashCode());
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
			Usuario other = (Usuario) obj;
			if (codigoUsuario == null) {
				if (other.codigoUsuario != null)
					return false;
			} else if (!codigoUsuario.equals(other.codigoUsuario))
				return false;
			if (codigoUsuarioCadastro == null) {
				if (other.codigoUsuarioCadastro != null)
					return false;
			} else if (!codigoUsuarioCadastro.equals(other.codigoUsuarioCadastro))
				return false;
			if (complemento == null) {
				if (other.complemento != null)
					return false;
			} else if (!complemento.equals(other.complemento))
				return false;
			if (dataCadastro == null) {
				if (other.dataCadastro != null)
					return false;
			} else if (!dataCadastro.equals(other.dataCadastro))
				return false;
			if (dataNascimento == null) {
				if (other.dataNascimento != null)
					return false;
			} else if (!dataNascimento.equals(other.dataNascimento))
				return false;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (nomeUsuario == null) {
				if (other.nomeUsuario != null)
					return false;
			} else if (!nomeUsuario.equals(other.nomeUsuario))
				return false;
			if (referencia == null) {
				if (other.referencia != null)
					return false;
			} else if (!referencia.equals(other.referencia))
				return false;
			if (rg == null) {
				if (other.rg != null)
					return false;
			} else if (!rg.equals(other.rg))
				return false;
			if (senha == null) {
				if (other.senha != null)
					return false;
			} else if (!senha.equals(other.senha))
				return false;
			if (sexo == null) {
				if (other.sexo != null)
					return false;
			} else if (!sexo.equals(other.sexo))
				return false;
			if (telefoneCelular == null) {
				if (other.telefoneCelular != null)
					return false;
			} else if (!telefoneCelular.equals(other.telefoneCelular))
				return false;
			if (telefoneFixo == null) {
				if (other.telefoneFixo != null)
					return false;
			} else if (!telefoneFixo.equals(other.telefoneFixo))
				return false;
			if (usuarioAtivo == null) {
				if (other.usuarioAtivo != null)
					return false;
			} else if (!usuarioAtivo.equals(other.usuarioAtivo))
				return false;
			return true;
		}

		
		
}
