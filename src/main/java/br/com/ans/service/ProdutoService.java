package br.com.ans.service;

import java.util.List;

import br.com.ans.model.Produto;

public interface ProdutoService {

	public void novo(Produto produto);

	List<Produto> obterProdutoPorNome(String nome);

	void ativarInativar(Produto produto);

	Produto porCodigo(Long codigo);

	Produto porCodigoBarra(String codigoBarras);

}
