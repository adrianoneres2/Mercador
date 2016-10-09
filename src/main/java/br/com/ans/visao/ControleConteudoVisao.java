package br.com.ans.visao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "controleConteudoMBean")
@SessionScoped
public class ControleConteudoVisao {
	
	
	private String paginaConteudo;
	//private static final String CADASTRO_USUARIO = "/visao/usuario/cadastroUsuario.xhtml";

	public String getPaginaConteudo() {
		return paginaConteudo;
	}

	public void setPaginaConteudo(String paginaConteudo) {
		this.paginaConteudo = paginaConteudo;
	}

	public void definirPagina(){
		return;
	}
}
