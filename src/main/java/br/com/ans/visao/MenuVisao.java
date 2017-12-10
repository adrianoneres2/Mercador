package br.com.ans.visao;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class MenuVisao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String CADASTRO_USUARIO = "/visao/usuario/cadastroUsuario";
	private static final String CONSULTA_USUARIO = "/visao/usuario/consultaUsuario";
	private static final String ALTERAR_USUARIO = "/visao/usuario/alterarUsuario";
	private static final String APLICACAO = "/aplicacao";
	private static final String CADASTRO_PRODUTO = "/visao/produto/cadastroProduto";

	
	public String irCadastroUsuario(){
		
	 return CADASTRO_USUARIO;	
	
	}

	public String irConsultaUsuario(){
		 return CONSULTA_USUARIO;	
	}

	public String irAlterarUsuario(){
		 return ALTERAR_USUARIO;	
	}
	
	public String irAplicacao(){
		 return APLICACAO;	
	}
	
	public String irCadastroProduto(){
		 return CADASTRO_PRODUTO;	
	}
	
}
