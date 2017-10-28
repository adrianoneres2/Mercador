package br.com.ans.visao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ans.model.Categoria;
import br.com.ans.model.Produto;
import br.com.ans.model.SubCategoria;
import br.com.ans.service.CategoriaService;
import br.com.ans.service.ProdutoService;
import br.com.ans.service.SubCategoriaService;

@javax.faces.view.ViewScoped
@Named
public class produtoVisao implements Serializable{

	private static final long serialVersionUID = 1L;

	HashMap<Long,String> listaCategoria;
	
	HashMap<Long,String> listaSubCategoria;
	
	private List<Categoria> categorias;
	private List<SubCategoria> subCategorias;
	
	@Inject
	private CategoriaService categoriaService;
	
	@Inject SubCategoriaService subCategoriaService;
	
	private Long categoriaSelecionada;
	private Long subCategoriaSelecionada;
	
	@Inject
	private Produto produto;
	
	@Inject ProdutoService produtoService;
	
	
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
	
	

	public Long getSubCategoriaSelecionada() {
		return subCategoriaSelecionada;
	}


	public void setSubCategoriaSelecionada(Long subCategoriaSelecionada) {
		this.subCategoriaSelecionada = (Long) subCategoriaSelecionada;
	}	

	public HashMap<Long, String> getListaSubCategoria() {
		return listaSubCategoria;
	}


	public void setListaSubCategoria(HashMap<Long, String> listaSubCategoria) {
		this.listaSubCategoria = listaSubCategoria;
	}

	

	public List<SubCategoria> getSubCategorias() {
		return subCategorias;
	}
	
	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public void setSubCategorias(List<SubCategoria> subCategorias) {
		this.subCategorias = subCategorias;
	}


	public void carregarListaCategoria(){
		
		this.setCategorias(this.categoriaService.todos());
		HashMap<Long, String> mapaCategoria = new HashMap<Long, String>();
		
		for(Categoria categoria : categorias){
			mapaCategoria.put(categoria.getCodigoCategoria(), categoria.getDescricaoCategoria());
		}
		
		this.setListaCategoria(mapaCategoria);
	}
	
	public void carregarListaSubCategoria(){
		
		this.setSubCategorias(this.subCategoriaService.porCategoria(this.getCategoriaSelecionada()));
		HashMap<Long, String> mapaSubCategoria = new HashMap<Long, String>();
		
		for(SubCategoria subCategoria : subCategorias){
			mapaSubCategoria.put(subCategoria.getCodigoSubCategoria(), subCategoria.getDescricaoSubCategoria());
		}
		
		this.setListaSubCategoria(mapaSubCategoria);
	}
	
	public void novo(){
		
		for(SubCategoria subcategoria : subCategorias){
			
			if (subcategoria.getCodigoSubCategoria().equals(this.getSubCategoriaSelecionada())){
				produto.setSubCategoria(subcategoria);
			}
		}
		
		produtoService.novo(produto);
		
	}
	
}
