package br.com.ans.visao;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class MenuVisao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String CADASTRO_USUARIO = "/visao/usuario/cadastroUsuario";

	
	public String irCadastroUsuario(){
	 return CADASTRO_USUARIO;	
	}
	
}
