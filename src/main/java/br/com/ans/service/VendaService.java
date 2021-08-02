package br.com.ans.service;

import br.com.ans.model.ItemVenda;
import br.com.ans.model.Usuario;
import br.com.ans.model.Venda;

public interface VendaService {

	public Venda buscarVenda(Usuario usuarioLogado);
	public Venda atualizar(Venda venda);
	public Venda finalizarVenda(Venda venda);
	public ItemVenda adicionarItemVenda(ItemVenda itemVenda);
	public ItemVenda cancelarItemVenda(ItemVenda itemVenda, Usuario usuarioAutorizador);
}
