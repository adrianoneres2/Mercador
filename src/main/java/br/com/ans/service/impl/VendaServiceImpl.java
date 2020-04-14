package br.com.ans.service.impl;


import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.ans.dao.VendaDao;
import br.com.ans.model.Usuario;
import br.com.ans.model.Venda;
import br.com.ans.service.CaixaService;
import br.com.ans.service.SituacaoVendaService;
import br.com.ans.service.TipoVendaService;
import br.com.ans.service.VendaService;

@RequestScoped
public class VendaServiceImpl implements VendaService {
	
	@Inject
	private SituacaoVendaService situacaoVendaService;

	@Inject
	private CaixaService caixaService;
	
	@Inject
	private TipoVendaService tipoVendaService;
	
	@Inject
	private VendaDao vendaDao;
	

	private Venda venda;
	
	@Override
	public Venda buscarVenda(Usuario usuarioLogado) {
		venda = vendaDao.buscarVenda(usuarioLogado);
		
		if(venda == null){
			venda = new Venda();
			venda.setDataVenda(new Date());
			venda.setSituacaoVenda(situacaoVendaService.porCodigo(2L));
			venda.setUsuarioCliente(usuarioLogado);
			venda.setCaixa(caixaService.getCaixaAberto(usuarioLogado));
			venda.setTipoVenda(tipoVendaService.porCodigo(1L));
			if (venda.getCaixa() != null){
				return vendaDao.novaVenda(venda);
			}
			return vendaDao.novaVenda(venda);
		}
		return venda;
	}

}
