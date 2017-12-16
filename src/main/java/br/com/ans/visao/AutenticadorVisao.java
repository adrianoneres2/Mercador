package br.com.ans.visao;


import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ans.model.Usuario;
import br.com.ans.service.AutenticadorService;

@SessionScoped
@Named
public class AutenticadorVisao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AutenticadorService autenticadorService;
	
	@Inject
	private Usuario usuario;
	
	private Long codigoAuxiliar;
	
	
    private final String APLICACAO = "/aplicacao";
    private boolean logado = false;
	
    public AutenticadorVisao(){
    }
    
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean isLogado) {
		this.logado = isLogado;
	}	
	
	public AutenticadorService getAutenticadorService() {
		return autenticadorService;
	}

	public void setAutenticadorService(AutenticadorService autenticadorService) {
		this.autenticadorService = autenticadorService;
	}

	public String logar(){
		
		usuario = getAutenticadorService().validarLogin(this.getUsuario()) ;

		/*
		Collection <PerfilFuncionalidade> pfs;
		
		pfs = usuario.getPerfil().getPerfilFuncionalidade();
		
		for(PerfilFuncionalidade pf : pfs){
			System.out.println(pf.getFuncionalidade().getNomeFuncionalidade());
			System.out.println(pf.getFuncionalidade().getCodigoFuncionalidade());
			
		}
*/
		
		if(usuario != null){
			this.setLogado(true);
			return APLICACAO;
		}else{
			//Limpa a instancia de usuário atual.
			usuario = new Usuario();
			return null;
		}
	}
	
	public String sair(){
		if(this.isLogado()){
			this.setLogado(false);
			usuario = new Usuario();
			return APLICACAO;
		}else{
			return null;
		}
	}

	public Long getCodigoAuxiliar() {
		return codigoAuxiliar;
	}

	public void setCodigoAuxiliar(Long codigoAuxiliar) {
		this.codigoAuxiliar = codigoAuxiliar;
	}
}
