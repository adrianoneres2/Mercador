package br.com.ans.dao;

import br.com.ans.model.ItemVenda;
import br.com.ans.model.Produto;
import br.com.ans.model.Usuario;
import br.com.ans.model.Venda;

public interface VendaDao {
	public Venda buscarVenda(Usuario usuarioLogado);
	public Venda novaVenda(Venda venda);
	public Venda atualizar(Venda venda);
	public Venda finalizarVenda(Venda venda);
	public ItemVenda adicionarItemVenda(ItemVenda itemVenda);
	public ItemVenda buscaItemVendaPorVenda(Venda venda);
	public ItemVenda buscaItemVendaPorProduto(ItemVenda produtoItemVenda);
	
}
