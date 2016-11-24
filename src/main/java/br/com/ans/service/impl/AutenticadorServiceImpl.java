package br.com.ans.service.impl;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.ans.dao.AutenticadorDao;
import br.com.ans.model.Usuario;
import br.com.ans.service.AutenticadorService;

@RequestScoped
public class AutenticadorServiceImpl implements AutenticadorService {
	
	@Inject
	private AutenticadorDao  autenticadorDao;
	
	@Override
	public Usuario validarLogin(Usuario usuario) {
			if(!usuario.getEmail().trim().isEmpty() && !usuario.getSenha().trim().isEmpty()){
				Usuario usuarioLogado = this.logar(usuario.getEmail(), usuario.getSenha());
				if(usuarioLogado != null){
					return usuarioLogado;
				}else{
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Não foi possível efetuar o login!."));
						return null;
				}
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Parâmetros inválidos!."));
				return null;
			}
		}

	@Override
	public Usuario logar(String email, String senha){
		//UsuarioDao usuarioDao = new UsuarioDao();
		return autenticadorDao.validarUsuarioLogin(email, senha);
	}
}
