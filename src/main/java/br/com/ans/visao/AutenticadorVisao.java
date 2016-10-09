package br.com.ans.visao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.ans.modelo.Usuario;
import br.com.ans.service.UsuarioService;
import br.com.ans.service.impl.UsuarioServiceImpl;

@ManagedBean
@SessionScoped
//@Named
public class AutenticadorVisao {
	
	private UsuarioServiceImpl usuarioServiceImpl;
    private final String LOGIN = "/index";
    private boolean logado = false;
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
    public AutenticadorVisao(){
    	this.setUsuario(new Usuario());
    	this.setUsuarioServiceImpl(new UsuarioServiceImpl());
    }

	public UsuarioService getUsuarioServiceImpl() {
		return usuarioServiceImpl;
	}

	public void setUsuarioServiceImpl(UsuarioServiceImpl usuarioServiceImpl) {
		this.usuarioServiceImpl = usuarioServiceImpl;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean isLogado) {
		this.logado = isLogado;
	}

	public String logar(){
		if(usuarioServiceImpl.validarLogin(this.getUsuario())){
			this.setLogado(true);
			return LOGIN;
		}else{
			return null;
		}
	}
	
	public String sair(){
		if(this.isLogado()){
			this.setLogado(false);
			usuario = new Usuario();
			return LOGIN;
		}else{
			return null;
		}
	}
}
