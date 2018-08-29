package br.com.ans.service.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.ans.dao.PerfilFuncionalidadeDao;
import br.com.ans.model.PerfilFuncionalidade;
import br.com.ans.service.PerfilFuncionalidadeService;

@RequestScoped
public class PerfilFuncionalidadeServiceImpl implements PerfilFuncionalidadeService{

	@Inject
	PerfilFuncionalidadeDao perfilFuncionalidadeDao;
	
	@Override
	public List<PerfilFuncionalidade> listarPerfil(Long codigoPerfil){
		return perfilFuncionalidadeDao.listarPerfil(codigoPerfil);
	}
	
	@Override
	public boolean perfilFuncionalidade(Long codigoPerfil, Long codFuncionalidade){
		return perfilFuncionalidadeDao.perfilFuncionalidade(codigoPerfil, codFuncionalidade);
	}
	
}
