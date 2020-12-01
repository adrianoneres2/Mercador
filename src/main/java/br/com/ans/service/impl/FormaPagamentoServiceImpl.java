package br.com.ans.service.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.ans.dao.FormaPagamentoDao;
import br.com.ans.model.Bandeira;
import br.com.ans.model.FormaPagamento;
import br.com.ans.service.FormaPagamentoService;

/*
 * O @RequestScoped de todos os services impl tanto dos "Services como dos Daos" devem ser anotados com a opção do context javax.enterprise.context.RequestScoped; para conserguir injetar estas classes.
 * */ 

@RequestScoped
public class FormaPagamentoServiceImpl implements FormaPagamentoService{

	
	@Inject
	private FormaPagamentoDao formaPagamentoDao;
	
	@Override
	public List<FormaPagamento> todas(){
		return formaPagamentoDao.todas();
	}

	@Override
	public List<Bandeira> bandeiras() {
		return formaPagamentoDao.bandeiras();
	}
	
	
	
}
