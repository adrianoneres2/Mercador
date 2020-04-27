package br.com.ans.service;

import java.util.List;

import br.com.ans.model.Autorizacao;

public interface AutorizacaoService  {

	public List<Autorizacao> todas();

	public Autorizacao porCodigoAutorizacao(Long codigoAutorizacao);

}
