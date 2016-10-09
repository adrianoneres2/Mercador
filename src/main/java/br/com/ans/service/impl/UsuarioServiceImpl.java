package br.com.ans.service.impl;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.ans.dao.UsuarioDao;
import br.com.ans.modelo.Usuario;
import br.com.ans.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {
	

	private static final long serialVersionUID = 1L;
	
	public Usuario logar(String email, String senha){
		UsuarioDao usuarioDao = new UsuarioDao();
		return usuarioDao.validarUsuarioLogin(email, senha);
	}

	public Usuario obterUsuarioPorEmail(String email){
		UsuarioDao usuarioDao = new UsuarioDao();
		return usuarioDao.consultarUsuarioPorEmail(email);
	}

	public boolean consultarRgUsuario(Integer rg){
		UsuarioDao usuarioDao = new UsuarioDao();
		return usuarioDao.consultarRgUsuario(rg);
	}
	
	public boolean validarLogin(Usuario usuario){
		if(!usuario.getEmail().trim().isEmpty() && !usuario.getSenha().trim().isEmpty()){
			Usuario usuarioLogado = this.logar(usuario.getEmail(), usuario.getSenha());
			if(usuarioLogado != null){
				usuario = usuarioLogado;
				return true;
			}else{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Não foi possível efetuar o login!."));
					return false;
			}
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Parâmetros inválidos!."));
			return false;
		}
	}
	
	public void cadastrarUsuario(Usuario usuario){
        UsuarioDao uDao = new UsuarioDao();
        try{
           uDao.salvar(usuario);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Cadastrado com sucesso!"));
        }catch(Exception e){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fatal", "Erro ao tentar cadastrar usuário!"));
        	e.printStackTrace();
        }
	}
}
