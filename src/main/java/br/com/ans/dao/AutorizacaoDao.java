package br.com.ans.dao;

import java.util.List;

import br.com.ans.model.Autorizacao;

public interface AutorizacaoDao {

	List<Autorizacao> todas();
	public Autorizacao porCodigo(Long codigoAutorizacao);

}
