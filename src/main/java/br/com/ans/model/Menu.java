package br.com.ans.model;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class Menu {

	private String nomeMenu;
	private String itemMenu;
	private String pagina;
	
	
	public String getNomeMenu() {
		return nomeMenu;
	}
	public void setNomeMenu(String nomeMenu) {
		this.nomeMenu = nomeMenu;
	}
	public String getItemMenu() {
		return itemMenu;
	}
	public void setItemMenu(String itemMenu) {
		this.itemMenu = itemMenu;
	}
	public String getPagina() {
		return pagina;
	}
	public void setPagina(String pagina) {
		this.pagina = pagina;
	}
}
