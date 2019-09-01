package br.com.ans.dao;

import br.com.ans.model.Caixa;
import br.com.ans.model.Usuario;


public interface CaixaDao {

	void abrirCaixa(Caixa caixa) throws Exception;

	Caixa getCaixaAberto(Usuario usuarioOperador);

	void fecharCaixa(Caixa caixa) throws Exception;

}
