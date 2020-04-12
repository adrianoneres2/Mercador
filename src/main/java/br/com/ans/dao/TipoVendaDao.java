package br.com.ans.dao;

import java.util.List;

import br.com.ans.model.TipoVenda;

public interface TipoVendaDao {

	List<TipoVenda> todas();
	public TipoVenda porCodigo(Long codigoTipoVenda);

}
