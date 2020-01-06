package br.com.ans.service;

import br.com.ans.model.Caixa;
import br.com.ans.model.Usuario;


public interface CaixaService {

	public Caixa abrirCaixa(Caixa caixa);

	Caixa fecharCaixa(Caixa caixa);
	public Caixa getCaixaAberto(Usuario usuarioLogado);	

}
