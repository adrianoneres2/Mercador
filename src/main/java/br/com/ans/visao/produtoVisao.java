package br.com.ans.visao;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ans.model.Categoria;
import br.com.ans.model.Produto;
import br.com.ans.model.SubCategoria;
import br.com.ans.service.CategoriaService;
import br.com.ans.service.ProdutoService;
import br.com.ans.service.SubCategoriaService;
import enumerations.FuncionalidadeEnum;

@javax.faces.view.ViewScoped
@Named
public class produtoVisao implements Serializable{

	private static final long serialVersionUID = 1L;

	HashMap<Long,String> listaCategoria;
	
	HashMap<Long,String> listaSubCategoria;
	
	private List<Categoria> categorias;
	private List<SubCategoria> subCategorias;
	
	private List<Produto> produtos;
	
	@Inject
	private CategoriaService categoriaService;
	
	@Inject SubCategoriaService subCategoriaService;
	
	private Long categoriaSelecionada;
	private Long subCategoriaSelecionada;
	
	@Inject
	private Produto produto;
	
	@Inject ProdutoService produtoService;
	
	@Inject
	private AutenticadorVisao usuarioLogado;
	
	@Inject
	MenuVisao menuVisao;  
	
	@PostConstruct
	public void init(){
		this.carregarListaCategoria();
		
		Produto produtoAlteracao = new Produto();
		produtoAlteracao = (Produto) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("produto");
		
		if (produtoAlteracao != null){
			this.produto = produtoAlteracao;
			this.categoriaSelecionada    = this.produto.getSubCategoria().getCategoria().getCodigoCategoria();
			this.subCategoriaSelecionada = this.produto.getSubCategoria().getCodigoSubCategoria();
			/*Necessário para carregar no componente comboBox a subcategoria com o valor setado acima, funciona como um refresh na página!!!0,.*/
			this.carregarListaSubCategoria();
		}
		
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

	public List<Produto> getProdutos() {
		return produtos;
	}


	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}


	public void carregarListaCategoria(){
		
		this.setCategorias(this.categoriaService.todos());
		HashMap<Long, String> mapaCategoria = new HashMap<Long, String>();
		
		for(Categoria categoria : categorias){
			mapaCategoria.put(categoria.getCodigoCategoria(), categoria.getNomeCategoria());
		}
		
		this.setListaCategoria(mapaCategoria);
	}
	
	public void carregarListaSubCategoria(){
		
		this.setSubCategorias(this.subCategoriaService.porCategoria(this.getCategoriaSelecionada()));
		HashMap<Long, String> mapaSubCategoria = new HashMap<Long, String>();
		
		for(SubCategoria subCategoria : subCategorias){
			mapaSubCategoria.put(subCategoria.getCodigoSubCategoria(), subCategoria.getNomeSubCategoria());
		}
		
		this.setListaSubCategoria(mapaSubCategoria);
	}
	
	public void novo(){
		
		if (this.getSubCategoriaSelecionada() != 0L){
			for(SubCategoria subcategoria : subCategorias){
				
				if (subcategoria.getCodigoSubCategoria().equals(this.getSubCategoriaSelecionada())){
					produto.setSubCategoria(subcategoria);
				}
			}
		}
		
		this.getProduto().setDataProduto(new Date());
		this.getProduto().setSituacaoProduto(1);
		produtoService.novo(produto);
		produto = new Produto();
		
	}
	
	public String acessarFuncionalidade(FuncionalidadeEnum funcionalidadeEnum){
		return menuVisao.acessar(usuarioLogado.getUsuario(), funcionalidadeEnum);
	}
	
	public String consultarProduto(){
		String consulta = acessarFuncionalidade(FuncionalidadeEnum.CONSULTAPRODUTO);
		
		if(consulta != null){
			consultarProdutoPorNome();
			return consulta;
		}
	  return null;
	}
	
	public void consultarProdutoPorNome(){
			
			if(produto == null){
				setProdutos(produtoService.obterProdutoPorNome(""));
			}else{
				setProdutos(produtoService.obterProdutoPorNome(produto.getNomeProduto()));
			}
	}
	
	public void ativarInativar(Produto produto){ 
		if(acessarFuncionalidade(FuncionalidadeEnum.ATIVARINATIVARPRODUTO) != null){
			produtoService.ativarInativar(produto);
			this.consultarProdutoPorNome();
		}		
	}
	
	public String editarProduto(Produto produtoEdicao) {
		String permissao = acessarFuncionalidade(FuncionalidadeEnum.ALTERAPRODUTO);
		
		if(permissao != null){
			  /*Guarda o objeto usuário na sessão flash.*/
			  FacesContext.getCurrentInstance().getExternalContext().getFlash().put("produto", produtoEdicao);
			  return permissao;
	  }else{
        return null;
	  }
	}
	
	public void alterar() throws Exception {
		/*Seta a categoria selecionada na tela de alteração ou cadastro*/ 
		produto.getSubCategoria().setCodigoSubCategoria(this.getSubCategoriaSelecionada());
		produtoService.novo(produto);
	}
}
