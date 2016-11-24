package br.com.ans.service.impl;

import java.util.Date;

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

		if (usuario.getNomeUsuario() == null || usuario.getNomeUsuario().equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo usuário é obrigatório!"));
			validador = false;
		}

			if (usuario.getSenha() == null || usuario.getSenha().equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo senha é obrigatório!"));
				validador = false;
			}

			if (usuario.getRg() == null || usuario.getRg() == 0) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo RG é obrigatório!"));
				validador = false;
			} else {
				if (this.consultarRgUsuario(usuario.getRg())) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "RG já cadastrado na base de dados!"));
					validador = false;
				}
			}
			
			if (usuario.getTelefoneCelular() == null || usuario.getTelefoneCelular().equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo telefone celular é obrigatório!"));
				validador = false;
			}

			if (usuario.getDataNascimento() == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo Data de nascimento é obrigatório!"));
				validador = false;
			} else {

				if (new Date().before(usuario.getDataNascimento())) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo Data de nascimento é maior que a data atual"));
					validador = false;
				}
			}

			if (usuario.getSexo() == null || usuario.getSexo().equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo sexo é obrigatório!"));
				validador = false;
			}

			if (usuario.getEmail() == null || usuario.getEmail().equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo e-mail é obrigatório!"));
				validador = false;
			} else {
				if (this.obterUsuarioPorEmail(usuario.getEmail()) != null ) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "E-mail já cadastrado na base de dados!"));
					validador = false;
				}
			}
			return validador;
	}
	
}
