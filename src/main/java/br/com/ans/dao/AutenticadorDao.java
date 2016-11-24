package br.com.ans.dao;

import br.com.ans.model.Usuario;

public interface AutenticadorDao {

	Usuario validarUsuarioLogin(String email, String senha);
}
