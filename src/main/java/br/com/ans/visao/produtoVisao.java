package br.com.ans.visao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ans.model.Categoria;
import br.com.ans.service.CategoriaService;

@javax.faces.view.ViewScoped
@Named
public class produtoVisao implements Serializable{

	private static final long serialVersionUID = 1L;

	HashMap<Long,String> listaCategoria;
	
	private List<Categoria> categorias;	
	
	@Inject
	private CategoriaService categoriaService;
	
	private Long categoriaSelecionada;
	
	
	@PostConstruct
	public void init(){
		this.carregarListaCategoria();
	}
	
	
	public HashMap<Long, String> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(HashMap<Long, String> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}


	public CategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public Long getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(Long categoriaSelecionada) {
		this.categoriaSelecionada = (Long)categoriaSelecionada;
	}

	public void carregarListaCategoria(){
		
		this.setCategorias(this.categoriaService.todos());
		HashMap<Long, String> mapaCategoria = new HashMap<Long, String>();
		
		for(Categoria categoria : categorias){
			mapaCategoria.put(categoria.getCodigoCategoria(), categoria.getDescricaoCategoria());
		}
		
		this.setListaCategoria(mapaCategoria);
	}
	
}
