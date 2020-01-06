package br.com.ans.service.impl;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.ans.dao.CaixaDao;
import br.com.ans.model.Caixa;
import br.com.ans.model.Usuario;
import br.com.ans.service.CaixaService;

@RequestScoped
public class CaixaServiceImpl implements CaixaService {

	@Inject
	private CaixaDao caixaDao;
	
	@Override
	public Caixa abrirCaixa(Caixa caixa) {
	  
	  Caixa caixaAberto = new Caixa();
	  try {
		  /*Tenta recuperar um caixa existente para o usuário.*/
		  caixaAberto = caixaDao.getCaixaAberto(caixa.getUsuarioOperador());
	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro ao tentar recuperar o caixa!"));
	}
	  
	  if (caixaAberto != null){
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Já existe caixa aberto para este usuário!"));
		    return null;
	  }else{
		  try {
			  caixa.setDataAbertura(new Date());
			  caixaDao.abrirCaixa(caixa);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro ao tentar abrir o caixa!"));
		}
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Caixa aberto com sucesso!"));
		  /*Retorna o caixa que acabou de ser criado.*/
		  return caixaDao.getCaixaAberto(caixa.getUsuarioOperador());
	  }
	}

	@Override
	public Caixa fecharCaixa(Caixa caixa){
	  
	  Caixa caixaAberto = new Caixa();
	  try {
		  /*Tenta recuperar um caixa existente para o usuário.*/
		  caixaAberto = caixaDao.getCaixaAberto(caixa.getUsuarioOperador());
	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro ao tentar recuperar o caixa!"));
	}
	  
	  if (caixaAberto == null){
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Não existe caixa aberto para este operador!"));
		    return null;
	  }else{
		  try {
			  caixaAberto.setUsuarioFechamento(caixa.getUsuarioFechamento());
			  caixaAberto.setDataFechamento(new Date());
			  caixaDao.fecharCaixa(caixaAberto);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro ao tentar fechar o caixa!"));
		}
		  
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Caixa fechado com sucesso!"));
		  /*Retorna o caixa que acabou de ser criado.*/
		  return caixaAberto;
	  }
	}

	@Override
	public Caixa getCaixaAberto(Usuario usuarioLogado) {
		Caixa caixa = new Caixa();
		caixa = caixaDao.getCaixaAberto(usuarioLogado);
		if(caixa != null){
			return caixa;
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Não existe caixa aberto para este operador!"));
		return null;
	}
	
}
