package br.com.ans.visao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.ans.dao.UsuarioDao;
import br.com.ans.modelo.Usuario;
import br.com.ans.service.impl.UsuarioServiceImpl;

@ManagedBean(name="usuarioMBean")
@RequestScoped
public class UsuarioVisao {
	
	private List<Usuario> usuarios;
	private Usuario usuario;
	private UsuarioServiceImpl usuarioServiceImpl;

	//private static final String CADASTRO_USUARIO = "/visao/usuario/cadastroUsuario";
	
	public UsuarioVisao() {
		this.setUsuarios(new ArrayList<Usuario>());
		this.setUsuario(new Usuario());
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

	public UsuarioServiceImpl getUsuarioServiceImpl() {
		if (usuarioServiceImpl == null){
			usuarioServiceImpl = new UsuarioServiceImpl();
			return usuarioServiceImpl;
		}else{
			return usuarioServiceImpl;
		}
	}

	public void setUsuarioServiceImpl(UsuarioServiceImpl usuarioServiceImpl) {
		this.usuarioServiceImpl = usuarioServiceImpl;
	}

	public void inserirNovo() throws Exception {
		if (validarCamposCadastro()){
           getUsuarioServiceImpl().cadastrarUsuario(this.usuario);
		}
	}
	
	public void consultarUsuarios(){
            UsuarioDao uDao = new UsuarioDao();
            this.setUsuarios(uDao.bucarTodosOsUsuarios());
	}

	private boolean validarCamposCadastro() {

		boolean validador = true;

		if (this.usuario.getNomeUsuario() == null || this.usuario.getNomeUsuario().equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo usuário é obrigatório!"));
			validador = false;
		}

			if (this.usuario.getSenha() == null || this.usuario.getSenha().equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo senha é obrigatório!"));
				validador = false;
			}

			if (this.usuario.getRg() == 0) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo RG é obrigatório!"));
				validador = false;
			} else {
				if (this.getUsuarioServiceImpl().consultarRgUsuario(this.usuario.getRg())) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "RG já cadastrado na base de dados!"));
					validador = false;
				}
			}
			
			if (this.usuario.getTelefoneCelular() == null || this.usuario.getTelefoneCelular().equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo telefone celular é obrigatório!"));
				validador = false;
			}

			if (this.usuario.getDataNascimento() == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo Data de nascimento é obrigatório!"));
				validador = false;
			} else {

				if (new Date().before(this.usuario.getDataNascimento())) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo Data de nascimento é maior que a data atual"));
					validador = false;
				}
			}

			if (this.usuario.getSexo() == null || this.usuario.getSexo().equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo sexo é obrigatório!"));
				validador = false;
			}

			if (this.usuario.getEmail() == null || this.usuario.getEmail().equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo e-mail é obrigatório!"));
				validador = false;
			} else {
				if (this.getUsuarioServiceImpl().obterUsuarioPorEmail(this.usuario.getEmail()) != null ) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "E-mail já cadastrado na base de dados!"));
					validador = false;
				}
			}
			return validador;
	}

}
