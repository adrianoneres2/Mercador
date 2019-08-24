package br.com.ans.service.impl;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.ans.dao.CaixaDao;
import br.com.ans.model.Caixa;
import br.com.ans.service.CaixaService;

@RequestScoped
public class CaixaServiceImpl implements CaixaService {

	@Inject
	private CaixaDao caixaDao;
	
	@Override
	public Caixa abrirCaixa(Caixa caixa) throws Exception {
	  
	  Caixa caixaAberto = new Caixa();
	  caixaAberto = caixaDao.getCaixaAberto(caixa.getUsuarioOperador());
		
	  if (caixaAberto != null){
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Já existe caixa aberto para este usuário!"));
		    return null;
	  }else{
		  caixaDao.abrirCaixa(caixa);
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Caixa aberto com sucesso!"));
		  /*Retorna o caixa que acabou de ser criado.*/
		  return caixaDao.getCaixaAberto(caixa.getUsuarioOperador());
	  }
	}
	
}
