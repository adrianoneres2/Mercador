package br.com.ans.service;

import br.com.ans.model.Usuario;

public interface AutenticadorService {

	public Usuario logar(String email, String senha);
	public Usuario validarLogin(Usuario usuario);
}
