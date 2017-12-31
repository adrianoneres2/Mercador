package br.com.ans.service;

import java.util.List;

import br.com.ans.model.PerfilFuncionalidade;


public interface PerfilFuncionalidadeService {

	public List<PerfilFuncionalidade> porPerfil(Long codigoPerfil);

}
