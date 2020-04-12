package br.com.ans.service;

import java.util.List;

import br.com.ans.model.SituacaoVenda;

public interface SituacaoVendaService {

	public List<SituacaoVenda> todas();	
	public SituacaoVenda porCodigo(Long codigoSituacao);

}
