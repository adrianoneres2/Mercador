package br.com.ans.dao;

import java.util.List;

import br.com.ans.model.Usuario;

public interface UsuarioDao {

	boolean consultarRgUsuario(Integer rg);
	public Usuario consultarUsuarioPorEmail(String email);
	public List<Usuario> bucarTodos();
	void salvarUsuario(Usuario usuario) throws Exception;
	List<Usuario> consultarUsuarioPorNome(String nome);
	Usuario obterUsuarioPorCodigo(Long codigo);
}
