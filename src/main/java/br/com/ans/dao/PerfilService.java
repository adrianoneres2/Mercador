package br.com.ans.dao;

import java.util.List;

import br.com.ans.model.Perfil;


public interface PerfilService {

	public List<Perfil> obterPerfil();
	public Perfil obterPerfilPorCodigo(Long codigo);
	
}
