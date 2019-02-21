package br.com.ans.dao;

import java.util.List;

import br.com.ans.model.Produto;

public interface ProdutoDao {

	public boolean novo(Produto produto);

	List<Produto> consultarProdutoPorNome(String nome);

	List<Produto> bucarTodos();

}
