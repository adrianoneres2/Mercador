package br.com.ans.service.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.ans.dao.CaixaDao;
import br.com.ans.model.Caixa;
import br.com.ans.service.CaixaService;

@RequestScoped
public class CaixaServiceImpl implements CaixaService {

	@Inject
	private CaixaDao caixaDao;
	
	@Override
	public void abrirCaixa(Caixa caixa) throws Exception {
		caixaDao.abrirCaixa(caixa);
	}	
}
