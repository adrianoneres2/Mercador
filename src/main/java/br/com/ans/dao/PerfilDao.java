package br.com.ans.dao;

import java.util.List;

import br.com.ans.model.Perfil;

public interface PerfilDao {

	public List<Perfil> obterPerfil();
	public Perfil obterPerfilPorCodigo(Long codigo);
	public void salvarPerfil(Perfil perfil) throws Exception;
	
}
