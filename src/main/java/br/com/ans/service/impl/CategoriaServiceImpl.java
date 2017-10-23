package br.com.ans.service.impl;

import java.util.List;

import javax.inject.Inject;

import br.com.ans.dao.CategoriaDao;
import br.com.ans.model.Categoria;
import br.com.ans.service.CategoriaService;

public class CategoriaServiceImpl implements CategoriaService{

	
	@Inject
	CategoriaDao categoriaDao;
	
	@Override
	public List<Categoria> todos(){
		return categoriaDao.todas();
	}
}
