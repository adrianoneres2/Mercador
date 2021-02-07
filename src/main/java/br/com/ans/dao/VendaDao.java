package br.com.ans.dao;

import br.com.ans.model.Usuario;
import br.com.ans.model.Venda;

public interface VendaDao {
	public Venda buscarVenda(Usuario usuarioLogado);
	public Venda novaVenda(Venda venda);
	public Venda atualizar(Venda venda);
	public Venda finalizarVenda(Venda venda);
	
}
