package br.com.ans.dao;

import java.util.List;

import br.com.ans.model.PerfilFuncionalidade;


public interface PerfilFuncionalidadeDao {

	public List<PerfilFuncionalidade> listarPerfil(Long codigoPerfil);

	boolean perfilFuncionalidade(Long codigoPerfil, Long codFuncionalidade);

}