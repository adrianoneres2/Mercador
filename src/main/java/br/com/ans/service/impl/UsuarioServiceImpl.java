package br.com.ans.service.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.ans.dao.UsuarioDao;
import br.com.ans.model.Usuario;
import br.com.ans.service.UsuarioService;

@RequestScoped
public class UsuarioServiceImpl implements UsuarioService {
	
	@Inject
	private UsuarioDao usuarioDao;

	@Override
	public Usuario obterUsuarioPorEmail(String email){
		//UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
		return usuarioDao.consultarUsuarioPorEmail(email);
	}
	
	@Override
	public boolean consultarRgUsuario(Integer rg){
		return usuarioDao.consultarRgUsuario(rg);
	}
	
	@Override
	public void cadastrarUsuario(Usuario usuario){
        try{
        	
        	usuarioDao.salvarUsuario(usuario);
        	
        		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Cadastrado com sucesso!"));
        }catch(Exception e){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fatal", "Erro ao tentar cadastrar usuário!"));
        	e.printStackTrace();
        }
	}
	
	@Override
	public boolean validarCamposCadastro(Usuario usuario) {

		boolean validador = true;


		if (usuario.getPerfil() == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo perfil é obrigatório!"));
			validador = false;
		}
		
		if (usuario.getNomeUsuario() == null || usuario.getNomeUsuario().equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo usuário é obrigatório!"));
			validador = false;
		}

		     /*Quando existe código, trata-se de uma alteração de cadastro que não altera senha*/
			if (usuario.getCodigoUsuario() == null || usuario.getCodigoUsuario().equals("")){
				if (usuario.getSenha() == null || usuario.getSenha().equals("")) {
					
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo senha é obrigatório!"));
					validador = false;
				}
			
				if (!usuario.getSenha().equals(usuario.getSenhaConfirmacao())) {
					
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Senhas não conferem!"));
					validador = false;
				}
			}
			
			if (usuario.getEmail() == null || usuario.getEmail().equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo e-mail é obrigatório!"));
				validador = false;
			} else {
				if (this.obterUsuarioPorEmail(usuario.getEmail()) != null && (usuario.getCodigoUsuario() == null || usuario.getCodigoUsuario().equals(""))) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "E-mail já cadastrado na base de dados!"));
					validador = false;
				}
			}
			return validador;
	}

	@Override
	public List<Usuario> obterUsuarioPorNome(String nome) {
		
		if (!"".equals(nome)){
			return usuarioDao.consultarUsuarioPorNome(nome);	
		}else{
			return usuarioDao.bucarTodos();	
		}
	}
	
	@Override
	public void excluir(Usuario usuario){
		
		if(usuario.getUsuarioAtivo().equals("S")){
			usuario.setUsuarioAtivo("N");	
		}else{
			usuario.setUsuarioAtivo("S");
		}
		
        try{
        	usuarioDao.salvarUsuario(usuario);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Atualizado com sucesso!"));
        }catch(Exception e){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fatal", "Erro ao tentar atualizar usuário!"));
        	e.printStackTrace();
        }
	}
	
	
}
