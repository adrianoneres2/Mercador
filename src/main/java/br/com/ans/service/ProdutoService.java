package br.com.ans.service;

import java.util.List;

import br.com.ans.model.Produto;

public interface ProdutoService {

	public void novo(Produto produto);

	List<Produto> obterProdutoPorNome(String nome);

}
