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
import br.com.ans.model.ItemVenda;
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
	
	@Inject
	private Venda venda;
	
    private Produto produto;
	
	@Inject
	VendaService vendaService;
	
	List<Produto> produtos;
	
	@Inject
	private ProdutoService produtoService;
	
	private ItemVenda itemVenda;
	
	private String codigoBarras;
	
	private Long quantidade;
	
	private Long numeroItem = 0L;
	
	private Boolean objetoAtualizado;
	
	private Double valorTotal = 0.0;
	
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
	
	public ItemVenda getItemVenda() {
		return itemVenda;
	}

	public void setItemVenda(ItemVenda itemVenda) {
		this.itemVenda = itemVenda;
	}
	
	public Double getValorTotal() {
		/*Soma valor total da venda*/
		
		valorTotal = 0.0;
		
		Iterator<ItemVenda> iteratorItemVenda = venda.getItemVenda().iterator();
		
		while(iteratorItemVenda.hasNext()) {
			ItemVenda item = iteratorItemVenda.next();
			/*Soma apenas os não cancelados*/
			///if(item.getAutorizacao().getCodigoAutorizacao() != 2L) {
			valorTotal = valorTotal+item.getValorTotal();	
			///}
		}
		return valorTotal;
	}
	
	/* --------------------------------------------------------------------------------------------- */

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
	
	public void adicionarItemVenda(Produto produto){
		if(produto != null){
		  objetoAtualizado = false;
		  numeroItem++;	
		  /*Gerar um novo objeto*/	
		  itemVenda	 = new ItemVenda();
			
		  if(itemVenda.getQuantidadeItem() == null ) {
			  itemVenda.setQuantidadeItem(this.getQuantidade());
		  }
			
		  itemVenda.setNumeroItem(numeroItem);
		  itemVenda.setValorItem(produto.getValorVenda());
		  itemVenda.setProduto(produto);
		  itemVenda.setVenda(this.getVenda());

		  Iterator<ItemVenda> itemVendaIterator = venda.getItemVenda().iterator();
		  
		  while(itemVendaIterator.hasNext()){
		              ItemVenda item = itemVendaIterator.next();
		              if(item.getProduto().getCodigoProduto().equals(produto.getCodigoProduto())){
		            	  item.setQuantidadeItem(this.getQuantidade()+item.getQuantidadeItem());
		            	  //item.setNumeroItem(item.getNumeroItem());
		            	  item.setValorTotal(item.getQuantidadeItem()*produto.getValorVenda());
		            	  numeroItem--;
		            	  objetoAtualizado = true;
		            	  //itemVendaIterator.remove();
		              }
		          }
		  	if(objetoAtualizado == false){
			  	itemVenda.setValorTotal(itemVenda.getQuantidadeItem()*produto.getValorVenda());
			  	this.venda.getItemVenda().add(itemVenda);
		  	}
	  }
   }

	public void consultarProduto(){
		if(getCodigoBarras().equals(null) || getQuantidade() != null){
			adicionarItemVenda(this.produtoService.porCodigoBarra(codigoBarras));
		}
	}
	
}
