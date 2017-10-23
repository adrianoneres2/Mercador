package br.com.ans.service.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.ans.dao.CategoriaDao;
import br.com.ans.model.Categoria;
import br.com.ans.service.CategoriaService;

/*
 * O @RequestScoped de todos os services impl tanto dos "Services como dos Daos" devem ser anotados com a opção do context javax.enterprise.context.RequestScoped; para conserguir injetar estas classes.
 * */ 

@RequestScoped
public class CategoriaServiceImpl implements CategoriaService{

	
	@Inject
	private CategoriaDao categoriaDao;
	
	@Override
	public List<Categoria> todos(){
		return categoriaDao.todas();
	}
}
