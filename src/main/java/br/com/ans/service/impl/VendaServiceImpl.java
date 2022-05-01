package br.com.ans.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.ans.dao.VendaDao;
import br.com.ans.model.ItemVenda;
import br.com.ans.model.SituacaoItem;
import br.com.ans.model.Usuario;
import br.com.ans.model.Venda;
import br.com.ans.service.AutenticadorService;
import br.com.ans.service.CaixaService;
import br.com.ans.service.SituacaoVendaService;
import br.com.ans.service.TipoVendaService;
import br.com.ans.service.VendaService;
import enumerations.FuncionalidadeEnum;

@RequestScoped
public class VendaServiceImpl implements VendaService {
	
	@Inject
	private SituacaoVendaService situacaoVendaService;

	@Inject
	private AutenticadorService autenticadorService;
	
	@Inject
	private CaixaService caixaService;
	
	@Inject
	private TipoVendaService tipoVendaService;
	
	@Inject
	private VendaDao vendaDao;
	
	private Venda venda;
	
	private List<ItemVenda> itemVendaAtivos = new ArrayList<ItemVenda>();
	
	@Override
	public Venda buscarVenda(Usuario usuarioLogado) {
		venda = vendaDao.buscarVenda(usuarioLogado);
		Long numeroItem = 0L;
		
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
		}
		
		/*Retorna apenas o itens ativos da venda*/
		for(ItemVenda item : venda.getListaItemVenda()) {
			if(item.getSituacaoItem().getCodigoSituacaoItem().equals(1L)) {
				
				item.setNumeroItem(numeroItem++);
				itemVendaAtivos.add(item);
			}
		}
		
		venda.setListaItemVenda(itemVendaAtivos);
		return venda;
	}
	
	@Override
	public Venda atualizar(Venda venda) {
		/*Atualiza venda. Por exemplo: Concluído,  Cancelado etc...*/
		return vendaDao.atualizar(venda);
	}
	
	@Override
	public Venda finalizarVenda(Venda venda) { 

		/*Valida se existe itens para a venda*/
		if(venda.getListaItemVenda().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Inclua itens nesta venda!"));
			return null;
		}	
		
		/*Valida se existe forma de pagamento*/
		if(venda.getListaVendaFormaPagamento().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Selecione uma forma de pagamento!"));
			return null;
		}	
		
		/*Valida venda já finalizada*/
		if(venda.getSituacaoVenda().getCodigoSituacaoVenda() == 1L) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Venda já finalizada!"));
			return null;			
		}
		
		/*Muda status da venda para finalizada*/
		venda.setSituacaoVenda(situacaoVendaService.porCodigo(1L));
		/*Seta data de finalização*/
		venda.setDataVendaFinalizada(new Date());
		return vendaDao.finalizarVenda(venda);	
	}
	
	@Override
	public ItemVenda adicionarItemVenda(ItemVenda itemVenda){
		return vendaDao.atualizarItemVenda(itemVenda);
	}

	@Override
	public ItemVenda cancelarItemVenda(ItemVenda itemVenda, Usuario usuarioAutorizador) {
		/*Valida se o usuário consegue autenticar e se tem perfil para excluir item da venda*/
		if(autenticadorService.autorizar(usuarioAutorizador, (long) FuncionalidadeEnum.CANCELARITEMVENDA.getCodigo())){
			SituacaoItem situacaoItem = new SituacaoItem();
			/*Seta situação como cancelado*/
			situacaoItem.setCodigoSituacaoItem(2L);
			itemVenda.setSituacaoItem(situacaoItem);
			return vendaDao.atualizarItemVenda(itemVenda);
		}
		return null;
	}

}
