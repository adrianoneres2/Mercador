package br.com.ans.service;

import java.util.List;

import br.com.ans.model.TipoVenda;

public interface TipoVendaService {

	public List<TipoVenda> todas();
	public TipoVenda porCodigo(Long codigoTipoVenda);

}
