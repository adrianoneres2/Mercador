package br.com.ans.service.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.ans.dao.PerfilDao;
import br.com.ans.dao.PerfilService;
import br.com.ans.model.Perfil;

@RequestScoped
public class PerfilServiceImpl implements PerfilService {

	public PerfilServiceImpl(){}
	
	@Inject
	private PerfilDao perfilDao;

	@Override
	public List<Perfil> obterPerfil(){
		return perfilDao.obterPerfil();
	}

	@Override
	public Perfil obterPerfilPorCodigo(Long codigo){
		return perfilDao.obterPerfilPorCodigo(codigo);
	}

	@Override
	public void ativarInativar(Perfil perfil) {
		
		if(perfil.getSituacaoPerfil()==1L){
			perfil.setSituacaoPerfil(0L);	
		}else{
			perfil.setSituacaoPerfil(1L);
		}
		
        try{
        	perfilDao.salvarPerfil(perfil);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Situação atualizada com sucesso!"));
        }catch(Exception e){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fatal", "Erro ao tentar atualizar usuário!"));
        	e.printStackTrace();
        }
		
	}
	
	
}