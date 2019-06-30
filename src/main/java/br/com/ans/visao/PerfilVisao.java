package br.com.ans.visao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import br.com.ans.dao.PerfilService;
import br.com.ans.model.Funcionalidade;
import br.com.ans.model.Perfil;
import enumerations.FuncionalidadeEnum;

@javax.faces.view.ViewScoped
@Named
public class PerfilVisao implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Perfil> perfis;
	
	private List<Perfil> perfisDestino;
	
	@Inject
	private PerfilService perfilService;
	
	@Inject
	private Perfil perfil;
	
	@Inject
	MenuVisao menuVisao;
	
	@Inject
	private AutenticadorVisao usuarioLogado;

	private List<Funcionalidade> funcionalidades;
	
	HashMap<Long,String> listaPerfil;
	
	DualListModel<Perfil> perfisPcikList;
	
	@PostConstruct
	public void init(){
		this.carregarListaPerfis();
	 perfisPcikList = new DualListModel<Perfil>(perfis, perfisDestino);
		
		perfisPcikList.getSource();
		perfisPcikList.getTarget();
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
	
	public List<Perfil> getPerfisDestino() {
		return perfisDestino;
	}
	
	public DualListModel<Perfil> getPerfisPcikList() {
		return perfisPcikList;
	}

	public void setPerfisPcikList(DualListModel<Perfil> perfisPcikList) {
		this.perfisPcikList = perfisPcikList;
	}

	public void setPerfisDestino(List<Perfil> perfisDestino) {
		this.perfisDestino = perfisDestino;
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
	
	public HashMap<Long, String> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(HashMap<Long, String> listaPerfil) {		
		this.listaPerfil = listaPerfil;
	}
	
	public void carregarListaPerfis(){
		
		this.setPerfis(getPerfilService().obterPerfil());
			
			HashMap<Long, String> mapaPerfil = new HashMap<Long, String>();
			///mapaPerfil.put(0L, "Selecionar");
			
			for (Perfil perfil : perfis) {
				mapaPerfil.put(perfil.getCodigoPerfil(), perfil.getNomePerfil());
			}
			this.setListaPerfil(mapaPerfil);
	}
	
}
