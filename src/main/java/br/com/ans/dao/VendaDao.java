package br.com.ans.dao;

import br.com.ans.model.ItemVenda;
import br.com.ans.model.Usuario;
import br.com.ans.model.Venda;

public interface VendaDao {
	public Venda buscarVenda(Usuario usuarioLogado);
	public Venda novaVenda(Venda venda);
	public Venda atualizar(Venda venda);
	public Venda finalizarVenda(Venda venda);
	public ItemVenda atualizarItemVenda(ItemVenda itemVenda);
	public ItemVenda buscaItemVendaPorProduto(ItemVenda produtoItemVenda);
	
}
