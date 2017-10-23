package br.com.ans.visao;

import java.io.Serializable;
import java.util.ArrayList;

import javax.inject.Named;

import br.com.ans.model.Categoria;
import br.com.ans.model.Genero;
import br.com.ans.model.SubCategoria;

@javax.faces.view.ViewScoped
@Named
public class produtoVisao implements Serializable{

	private static final long serialVersionUID = 1L;

	private ArrayList<Genero> genero;
	
	private ArrayList<Categoria> categoria;
	
	private ArrayList<SubCategoria> subCategoria;

	public ArrayList<Genero> getGenero() {
		return genero;
	}

	public void setGenero(ArrayList<Genero> genero) {
		this.genero = genero;
	}

	public ArrayList<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(ArrayList<Categoria> categoria) {
		this.categoria = categoria;
	}

	public ArrayList<SubCategoria> getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(ArrayList<SubCategoria> subCategoria) {
		this.subCategoria = subCategoria;
	}
	
}
