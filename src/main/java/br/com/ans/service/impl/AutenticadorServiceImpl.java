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

	@Inject
	private PerfilFuncionalidadeServiceImpl perfilFuncionalidadeServiceImpl;
	
	@Override
	public Usuario validarLogin(Usuario usuario) {
		
		if (usuario == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Informar todos os dados obrigatórios!."));
			return null;
		}
			if(!usuario.getEmail().trim().isEmpty() && !usuario.getSenha().trim().isEmpty()){
				Usuario usuarioLogado = this.logar(usuario.getEmail(), usuario.getSenha());
				if(usuarioLogado != null){
					
					if (usuarioLogado.getSituacaoUsuario()==0L){
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Usuário inativo!."));
						return null;
					}else{
						return usuarioLogado;
					}					
				}else{
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Não foi possível efetuar o login!."));
						return null;
				}
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Informe todos os campos!."));
				return null;
			}
		}

	@Override
	public Usuario logar(String email, String senha){
		//UsuarioDao usuarioDao = new UsuarioDao();
		return autenticadorDao.validarUsuarioLogin(email, senha);
	}
	
	@Override
	public Boolean autorizar(Usuario usuarioAutorizador, Long codigoFuncionalidade) {
		/*Valida a existência e retorna o usuário logado com sucesso caso exista*/
		usuarioAutorizador = validarLogin(usuarioAutorizador);
		
		if(usuarioAutorizador != null && perfilFuncionalidadeServiceImpl.perfilFuncionalidade(usuarioAutorizador.getPerfil().getCodigoPerfil(), codigoFuncionalidade)) {
			return true;
		}
		return false;
	}
}
