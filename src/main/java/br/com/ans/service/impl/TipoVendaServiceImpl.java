package br.com.ans.service.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.ans.dao.TipoVendaDao;
import br.com.ans.model.TipoVenda;
import br.com.ans.service.TipoVendaService;

/*
 * O @RequestScoped de todos os services impl tanto dos "Services como dos Daos" devem ser anotados com a opção do context javax.enterprise.context.RequestScoped; para conserguir injetar estas classes.
 * */ 

@RequestScoped
public class TipoVendaServiceImpl implements TipoVendaService{

	
	@Inject
	private TipoVendaDao tipoVendaDao;
	
	@Override
	public List<TipoVenda> todas(){
		return tipoVendaDao.todas();
	}
}
