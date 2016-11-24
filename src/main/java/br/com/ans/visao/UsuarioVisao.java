package br.com.ans.visao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ans.dao.PerfilService;
import br.com.ans.dominio.Perfil;
import br.com.ans.model.Usuario;
import br.com.ans.service.UsuarioService;

@Named
@RequestScoped
public class UsuarioVisao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Usuario> usuarios;
	
	private Usuario usuario;
	
	@Inject
	private PerfilService perfilService;
	
	private List<Perfil> perfis; 
	
	private List<SelectItem> perfilUsuario = new ArrayList<SelectItem>();
	 
	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private AutenticadorVisao usuarioLogado;
	
	public UsuarioVisao() {
		this.setUsuarios(new ArrayList<Usuario>());
		this.setUsuario(new Usuario());
	} 
	
	public void init(){
		this.setPerfis(getPerfilService().obterPerfil()); 
		
		//perfilUsuario = new ArrayList<SelectItem>();
		
		for(Perfil perfil : this.getPerfis()){
			perfilUsuario.add(new SelectItem(perfil.getCodigoPerfil(), perfil.getNomePerfil()));
		}
		
	//	this.setPerfilUsuario(p);
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public AutenticadorVisao getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(AutenticadorVisao usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	
	public PerfilService getPerfilService() {
		return perfilService;
	}

	public void setPerfilService(PerfilService perfilService) {
		this.perfilService = perfilService;
	}

	
	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public List<SelectItem> getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(List<SelectItem> perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

	public void inserirNovo() throws Exception {

		if (getUsuarioService().validarCamposCadastro(this.getUsuario())){
			
		   //Atribui usuário logado!!!	
		   this.getUsuario().setCodigoUsuarioCadastro(this.getUsuarioLogado().getUsuario().getCodigoUsuario());	
		   getUsuarioService().cadastrarUsuario(this.getUsuario());
		}
	}

}
