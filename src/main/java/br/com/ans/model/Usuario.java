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
import javax.persistence.Transient;

@Entity
@Table(name = "tb_usuario", schema = "loja")
@Dependent
public class Usuario implements EntidadeBase, Serializable{
	
		private static final long serialVersionUID = 1L;

		public Usuario(){}
		
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
		private Date dataCadastro;
				
		@Column(name = "nm_email", nullable = false)
		private String   email;
		
		@Column(name = "st_usuario", nullable = true)
		private Long   situacaoUsuario;
		
		@Column(name = "id_usuario_cadastro	", nullable = false)
		private Long  codigoUsuarioCadastro;
		
		@ManyToOne
		@JoinColumn(name = "id_perfil")
		private Perfil perfil;

		@Transient
		private String senhaConfirmacao;
		
		@Transient
		private String descricaoSituacao;
		
//		@Column(name = "id_perfil", nullable = false)
//		private Long codigoPerfil;
		
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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		public Long getSituacaoUsuario() {
			return situacaoUsuario;
		}
		public void setSituacaoUsuario(Long situacaoUsuario) {
			this.situacaoUsuario = situacaoUsuario;
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
				
		public Perfil getPerfil() {
			return perfil;
		}
		
		public void setPerfil(Perfil perfil) {
			this.perfil = perfil;
		}
		
		public String getSenhaConfirmacao() {
			return senhaConfirmacao;
		}
		public void setSenhaConfirmacao(String senhaConfirmacao) {
			this.senhaConfirmacao = senhaConfirmacao;
		}
		
		
		@Override
		public String toString() {
		    return String.format("%s[id=%d]", getClass().getSimpleName(), getCodigoUsuario());
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((codigoUsuario == null) ? 0 : codigoUsuario.hashCode());
			result = prime
					* result
					+ ((codigoUsuarioCadastro == null) ? 0
							: codigoUsuarioCadastro.hashCode());
			result = prime * result
					+ ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
			result = prime
					* result
					+ ((descricaoSituacao == null) ? 0 : descricaoSituacao
							.hashCode());
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result
					+ ((nomeUsuario == null) ? 0 : nomeUsuario.hashCode());
			result = prime * result
					+ ((perfil == null) ? 0 : perfil.hashCode());
			result = prime * result + ((senha == null) ? 0 : senha.hashCode());
			result = prime
					* result
					+ ((senhaConfirmacao == null) ? 0 : senhaConfirmacao
							.hashCode());
			result = prime
					* result
					+ ((situacaoUsuario == null) ? 0 : situacaoUsuario
							.hashCode());
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
			} else if (!codigoUsuarioCadastro
					.equals(other.codigoUsuarioCadastro))
				return false;
			if (dataCadastro == null) {
				if (other.dataCadastro != null)
					return false;
			} else if (!dataCadastro.equals(other.dataCadastro))
				return false;
			if (descricaoSituacao == null) {
				if (other.descricaoSituacao != null)
					return false;
			} else if (!descricaoSituacao.equals(other.descricaoSituacao))
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
			if (perfil == null) {
				if (other.perfil != null)
					return false;
			} else if (!perfil.equals(other.perfil))
				return false;
			if (senha == null) {
				if (other.senha != null)
					return false;
			} else if (!senha.equals(other.senha))
				return false;
			if (senhaConfirmacao == null) {
				if (other.senhaConfirmacao != null)
					return false;
			} else if (!senhaConfirmacao.equals(other.senhaConfirmacao))
				return false;
			if (situacaoUsuario == null) {
				if (other.situacaoUsuario != null)
					return false;
			} else if (!situacaoUsuario.equals(other.situacaoUsuario))
				return false;
			return true;
		}

		
}
