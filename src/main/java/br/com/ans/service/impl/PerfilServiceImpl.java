package br.com.ans.service.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.ans.dao.PerfilDao;
import br.com.ans.dao.PerfilService;
import br.com.ans.model.Perfil;

@RequestScoped
public class PerfilServiceImpl implements PerfilService {

	public PerfilServiceImpl(){}
	
	@Inject
	private PerfilDao perfilDao;

	@Override
	public List<Perfil> obterPerfil(){
		return perfilDao.obterPerfil();
	}

	@Override
	public Perfil obterPerfilPorCodigo(Long codigo){
		return perfilDao.obterPerfilPorCodigo(codigo);
	}
}