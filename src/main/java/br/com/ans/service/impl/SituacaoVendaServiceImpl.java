package br.com.ans.service.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.ans.dao.SituacaoVendaDao;
import br.com.ans.model.SituacaoVenda;
import br.com.ans.service.SituacaoVendaService;

/*
 * O @RequestScoped de todos os services impl tanto dos "Services como dos Daos" devem ser anotados com a opção do context javax.enterprise.context.RequestScoped; para conserguir injetar estas classes.
 * */ 

@RequestScoped
public class SituacaoVendaServiceImpl implements SituacaoVendaService{

	
	@Inject
	private SituacaoVendaDao situacaoVendaDao;
	
	@Override
	public List<SituacaoVenda> todas(){
		return situacaoVendaDao.todas();
	}
}
