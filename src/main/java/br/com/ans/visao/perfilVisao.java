package br.com.ans.visao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ans.dao.PerfilService;
import br.com.ans.model.Funcionalidade;
import br.com.ans.model.Perfil;
import enumerations.FuncionalidadeEnum;

@javax.faces.view.ViewScoped
@Named
public class perfilVisao implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Perfil> perfis;
	
	@Inject
	private PerfilService perfilService;
	
	@Inject
	private Perfil perfil;
	
	@Inject
	MenuVisao menuVisao;
	
	@Inject
	private AutenticadorVisao usuarioLogado;

	private List<Funcionalidade> funcionalidades;
	
	@PostConstruct
	public void init(){
	}
	
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
	
	public PerfilService getPerfilService() {
		return perfilService;
	}

	public void setPerfilService(PerfilService perfilService) {
		this.perfilService = perfilService;
	}

	public List<Funcionalidade> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(List<Funcionalidade> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public void perfis(){
		this.setPerfis(getPerfilService().obterPerfil());
	}
	
	public void funcionalidades(){
	  ///implementar
	}
	
	public void ativarInativar(Perfil perfil){
		if(acessarFuncionalidade(FuncionalidadeEnum.ATIVARINATIVARPERFIL) != null){
			perfilService.ativarInativar(perfil);
			this.setPerfis(getPerfilService().obterPerfil());
		}	
	}
	
	public String aplicacao(){ 
		 return acessarFuncionalidade(FuncionalidadeEnum.APLICACAO);
	}
	
	public String acessarFuncionalidade(FuncionalidadeEnum funcionalidadeEnum){
		return menuVisao.acessar(usuarioLogado.getUsuario(), funcionalidadeEnum);
	}
	
	public String editarPermissaoPerfil(Perfil perfil) {
		String permissao = acessarFuncionalidade(FuncionalidadeEnum.EDITARPERMISSAOUSUARIO);
/*		
		if(permissao != null){
			  FacesContext.getCurrentInstance().getExternalContext().getFlash().put("usuario", usuarioEdicao);
			  return permissao;
	  }else{
        return null;
	  }*/
		
		return permissao;
	}
	
}
