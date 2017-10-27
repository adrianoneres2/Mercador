package br.com.ans.dao;

import java.util.List;

import br.com.ans.model.SubCategoria;

public interface SubCategoriaDao {

public List<SubCategoria> todas();

public List<SubCategoria> porCategoria(Long idCategoria);

}
