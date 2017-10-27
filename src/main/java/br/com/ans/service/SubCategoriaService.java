package br.com.ans.service;

import java.util.List;

import br.com.ans.model.SubCategoria;

public interface SubCategoriaService  {

	public List<SubCategoria> todas();

	public List<SubCategoria> porCategoria(Long idCategoria);

}
