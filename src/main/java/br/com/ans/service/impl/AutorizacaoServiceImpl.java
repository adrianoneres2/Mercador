package br.com.ans.service.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.ans.dao.AutorizacaoDao;
import br.com.ans.model.Autorizacao;
import br.com.ans.service.AutorizacaoService;

@RequestScoped
public class AutorizacaoServiceImpl implements AutorizacaoService {

	public void AutorizacaoServiceImpl(){}
	
	@Inject
	private AutorizacaoDao autorizacaoDao;
	
	@Override
	public List<Autorizacao> todas(){
		return autorizacaoDao.todas();
	}

	@Override
	public Autorizacao porCodigoAutorizacao(Long codigaoAutorizacao){
		return autorizacaoDao.porCodigo(codigaoAutorizacao);
	}
	
}
