package br.com.ans.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ans.model.Bandeira;
import br.com.ans.model.Caixa;
import br.com.ans.model.FormaPagamento;
import br.com.ans.model.Produto;
import br.com.ans.model.Usuario;
import br.com.ans.model.Venda;
import br.com.ans.service.FormaPagamentoService;
import br.com.ans.service.ProdutoService;
import br.com.ans.service.VendaService;
import enumerations.FuncionalidadeEnum;

//@Dependent
//@SessionScoped
//@RequestScoped
@ViewScoped
@Named
public class VendaVisao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Caixa caixa;

	private Usuario usuarioCliente;

	@Inject
	MenuVisao menuVisao;
	
	@Inject
	private AutenticadorVisao usuarioLogado;
	
	private List<FormaPagamento> formasPagamento;
	
	private List<Bandeira> bandeiras;
	
	@Inject
	FormaPagamentoService formaPagamentoService;
	
	HashMap<Long,String> listaFormasPagamento;
	HashMap<Long,String> listaBandeiras;
	
	private Venda venda;
	
    private Produto produto;
	
	@Inject
	VendaService vendaService;
	
	List<Produto> produtos;
	
	@Inject
	private ProdutoService produtoService;
	
	private String codigoBarras;
	
	private Long quantidade;
	
	public VendaVisao() {
	}

	@PostConstruct
	public void init() {
		///Inicia a variável Produtos
		produtos = new ArrayList<Produto>();
		
		//Inicia objeto de venda e caixa 
		setVenda(vendaService.buscarVenda(usuarioLogado.getUsuario()));
		setCaixa(getVenda().getCaixa());
		
		carregarListaFormaPagamento();
		carregarListaBandeira();
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public Usuario getUsuarioCliente() {
		return usuarioCliente;
	}

	public void setUsuarioCliente(Usuario usuarioCliente) {
		this.usuarioCliente = usuarioCliente;
	}

	public AutenticadorVisao getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(AutenticadorVisao usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public List<FormaPagamento> getFormasPagamento() {
		return formasPagamento;
	}

	public void setFormasPagamento(List<FormaPagamento> formasPagamento) {
		this.formasPagamento = formasPagamento;
	}

	public HashMap<Long, String> getListaFormasPagamento() {
		return listaFormasPagamento;
	}

	public void setListaFormasPagamento(HashMap<Long, String> listaFormasPagamento) {
		this.listaFormasPagamento = listaFormasPagamento;
	}
	
	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}


	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	
	public List<Bandeira> getBandeiras() {
		return bandeiras;
	}

	public void setBandeiras(List<Bandeira> bandeiras) {
		this.bandeiras = bandeiras;
	}
	
	public HashMap<Long, String> getListaBandeiras() {
		return listaBandeiras;
	}

	public void setListaBandeiras(HashMap<Long, String> listaBandeiras) {
		this.listaBandeiras = listaBandeiras;
	}

	public String acessarFuncionalidade(FuncionalidadeEnum funcionalidadeEnum){
		String retorno = menuVisao.acessar(usuarioLogado.getUsuario(), funcionalidadeEnum);
		if(retorno == null){
			return null;
		}

		if (getVenda().getCaixa()!= null){
			return retorno;
		}
		return null;
	}	

	public void carregarListaFormaPagamento(){
		
		this.setFormasPagamento(formaPagamentoService.todas());
			HashMap<Long, String> mapaFormaPagamento = new HashMap<Long, String>();
			for (FormaPagamento formaPagamento : formasPagamento) {
				mapaFormaPagamento.put(formaPagamento.getCodigoFormaPagamento(), formaPagamento.getNomeFormaPagamento());
			}
			this.setListaFormasPagamento(mapaFormaPagamento);
	}

	
	public void carregarListaBandeira(){
		
		this.setBandeiras(formaPagamentoService.bandeiras());
			HashMap<Long, String> mapaBandeira = new HashMap<Long, String>();
			for (Bandeira bandeira : bandeiras) {
				mapaBandeira.put(bandeira.getCodigoBandeira(), bandeira.getNomeBandeira());
			}
			this.setListaBandeiras(mapaBandeira);
	}
	
	public void adicionarProduto(Produto produto){
		if(produto != null){
		  this.produto = produto; 
		  this.produto.setQuantidadeProduto(this.getQuantidade());	
		  this.produto.setValorTotal(getQuantidade()*produto.getValorVenda());
		  
		  Iterator<Produto> produtoLista = produtos.iterator();

		  while(produtoLista.hasNext()){
		              Produto prod = produtoLista.next();
		              if(prod.getCodigoProduto() == produto.getCodigoProduto()){
		            	  produto.setQuantidadeProduto(produto.getQuantidadeProduto()+prod.getQuantidadeProduto());
		            	  produtoLista.remove();
		              }
		          }
		  this.produtos.add(produto);
	  }
   }

	public void consultarProduto(){
		if(getCodigoBarras().equals(null) || getQuantidade() != null){
			adicionarProduto(this.produtoService.porCodigoBarra(codigoBarras));
		}
	}
	
}
