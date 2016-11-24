package br.com.ans.service;

import br.com.ans.model.Usuario;

public interface UsuarioService{
	
	public Usuario obterUsuarioPorEmail(String email);
	public void cadastrarUsuario(Usuario usuario);
	public boolean consultarRgUsuario(Integer rg);
	public boolean validarCamposCadastro(Usuario usuario);
}
