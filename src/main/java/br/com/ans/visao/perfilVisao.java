package br.com.ans.visao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ans.dao.PerfilService;
import br.com.ans.model.Perfil;

@javax.faces.view.ViewScoped
@Named
public class perfilVisao implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Perfil> perfis;
	
	@Inject
	private PerfilService perfilService;
	
	@Inject
	private Perfil perfil;
	
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

	public void perfis(){
		this.setPerfis(getPerfilService().obterPerfil());
	}
	
}
