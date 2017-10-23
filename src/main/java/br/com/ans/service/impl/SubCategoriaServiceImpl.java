package br.com.ans.service.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.ans.dao.SubCategoriaDao;
import br.com.ans.model.SubCategoria;
import br.com.ans.service.SubCategoriaService;

@RequestScoped
public class SubCategoriaServiceImpl implements SubCategoriaService {

	public void subCategoriaServiceImpl(){}
	
	@Inject
	private SubCategoriaDao subCategoriaDao;
	
	@Override
	public List<SubCategoria> todos(){
		return subCategoriaDao.todas();
	}
	
}
