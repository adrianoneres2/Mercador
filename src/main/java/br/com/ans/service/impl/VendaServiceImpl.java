package br.com.ans.service.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.ans.dao.VendaDao;
import br.com.ans.model.Usuario;
import br.com.ans.model.Venda;
import br.com.ans.service.VendaService;

@RequestScoped
public class VendaServiceImpl implements VendaService {

	@Inject
	private VendaDao vendaDao;
	
	@Override
	public Venda buscarVenda(Usuario usuarioLogado) {
		return vendaDao.buscarVenda(usuarioLogado);
	}

}
