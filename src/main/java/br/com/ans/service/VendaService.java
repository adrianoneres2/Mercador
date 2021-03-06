package br.com.ans.service;

import br.com.ans.model.Usuario;
import br.com.ans.model.Venda;

public interface VendaService {

	public Venda buscarVenda(Usuario usuarioLogado);
	public Venda atualizar(Venda venda);
	public Venda finalizarVenda(Venda venda);
}
