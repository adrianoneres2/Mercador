package br.com.ans.visao;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ans.model.Bandeira;
import br.com.ans.model.FormaPagamento;
import br.com.ans.model.ItemVenda;
import br.com.ans.model.Produto;
import br.com.ans.model.Usuario;
import br.com.ans.model.Venda;
import br.com.ans.model.VendaFormaPagamento;
import br.com.ans.service.FormaPagamentoService;
import br.com.ans.service.ProdutoService;
import br.com.ans.service.SituacaoVendaService;
import br.com.ans.service.VendaService;
import enumerations.FuncionalidadeEnum;
import util.Janela;

//@Dependent
//@SessionScoped
//@RequestScoped
//@ViewScoped
@javax.faces.view.ViewScoped
@Named
public class VendaVisao implements Serializable {

	private static final long serialVersionUID = 1L;

	//private Caixa caixa;

	private Usuario usuarioCliente;

	@Inject
	MenuVisao menuVisao;
	
	@Inject
	private AutenticadorVisao usuarioLogado;
	
	private List<FormaPagamento> formasPagamento;
	
	private List<Bandeira> bandeiras;
	
	private List<VendaFormaPagamento> vendaFormasPagamento;
	
	@Inject
	FormaPagamentoService formaPagamentoService;
	
	@Inject
	VendaFormaPagamento vendaFormaPagamento;
	
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
	
	private Long formaPagamentoSelecionada = 1L;
	
	private Long bandeiraSelecionada = 0L;
	
	private Double valorTotal = 0.0;
	
	private Double valorParcela = 0.0;
	
	private Double valorTotalParcela = 0.0;
	
	@Inject
	private SituacaoVendaService situacaoVendaService;
	
	public VendaVisao() {
	}

	@PostConstruct
	public void init() {
		///Inicia a variável Produtos
		produtos = new ArrayList<Produto>();
		
		//Inicia objeto de venda e caixa 
		//setVenda(vendaService.buscarVenda(usuarioLogado.getUsuario()));
		//novaVenda();
		//setCaixa(getVenda().getCaixa());
		
		carregarListaFormaPagamento();
		carregarListaBandeira();
	}

	/*
	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}
*/
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

	public Long getFormaPagamentoSelecionada() {
		return formaPagamentoSelecionada;
	}

	public void setFormaPagamentoSelecionada(Long formaPagamentoSelecionada) {
		this.formaPagamentoSelecionada = formaPagamentoSelecionada;
	}

	public Long getBandeiraSelecionada() {
		return bandeiraSelecionada;
	}

	public void setBandeiraSelecionada(Long bandeiraSelecionada) {
		this.bandeiraSelecionada = bandeiraSelecionada;
	}
	
	public Double getValorParcela() {
		return valorParcela;
	}
	
	public void setValorParcela(Double valorParcela) {
		this.valorParcela = valorParcela;
	}
	
	public Double getValorTotalParcela() {
		return valorTotalParcela;
	}

	public void setValorTotalParcela(Double valorTotalParcela) {
		this.valorTotalParcela = valorTotalParcela;
	}

	public List<VendaFormaPagamento> getVendaFormasPagamento() {
		return vendaFormasPagamento;
	}

	public void setVendaFormasPagamento(List<VendaFormaPagamento> vendaFormasPagamento) {
		this.vendaFormasPagamento = vendaFormasPagamento;
	}

	public VendaFormaPagamento getVendaFormaPagamento() {
		return vendaFormaPagamento;
	}

	public void setVendaFormaPagamento(VendaFormaPagamento vendaFormaPagamento) {
		this.vendaFormaPagamento = vendaFormaPagamento;
	}

	public Double getValorTotal() {
		return valorTotal;
	}
	
	
	public void calcularValorTotalVenda() {
		/*Soma valor total da venda*/
		Double valor = 0.0;
		
		Iterator<ItemVenda> iteratorItemVenda = venda.getItemVenda().iterator();
		
		while(iteratorItemVenda.hasNext()) {
			ItemVenda item = iteratorItemVenda.next();
			/*Soma apenas os não cancelados*/
			///if(item.getAutorizacao().getCodigoAutorizacao() != 2L) {
			valor = valor+item.getValorTotal();	
			///}
		}
		this.setValorTotal(valor);
		this.setValorParcela(valor);
	}
	
	public Double calcularValorTotalParcelaAdicionada() {
		/*Soma valor total das parcelas da venda*/
		Double valor = 0.0;
		
		Iterator<VendaFormaPagamento> listaVendaFormaPagamentoIterator = venda.getListaVendaFormaPagamento().iterator();
		
		while(listaVendaFormaPagamentoIterator.hasNext()) {
			VendaFormaPagamento item = listaVendaFormaPagamentoIterator.next();
			valor = valor+item.getValorParcela();
		}
		if(valor > 0) {
			this.setValorTotalParcela(valor);
		}else {
			this.setValorTotalParcela(getValorParcela());
		}
		return getValorTotalParcela();
	}

	
	/* --------------------------------------------------------------------------------------------- */

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	/*
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
*/

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

		  Iterator<ItemVenda> itemVendaIterator = venda.getItemVenda().iterator();
		  
		  while(itemVendaIterator.hasNext()){
		              ItemVenda item = itemVendaIterator.next();
		              if(item.getProduto().getCodigoProduto().equals(produto.getCodigoProduto())){
		            	  item.setQuantidadeItem(this.getQuantidade()+item.getQuantidadeItem());
		            	  //item.setNumeroItem(item.getNumeroItem());
		            	  item.setValorTotal(item.getQuantidadeItem()*produto.getValorVenda());
		            	  numeroItem--;
		            	  objetoAtualizado = true;
		      		  	/*Atualiza o item modificado*/
		      		  	setItemVenda(vendaService.adicionarItemVenda(item));
		              }
		          }
		   /*Se não foi atualizado insere como novo item*/
		  	if(objetoAtualizado == false){
				  /*Gerar um novo objeto*/	
				  itemVenda	 = new ItemVenda();
					
				  if(itemVenda.getQuantidadeItem() == null ) {
					  itemVenda.setQuantidadeItem(this.getQuantidade());
				  }
					
				  itemVenda.setNumeroItem(numeroItem);
				  itemVenda.setValorItem(produto.getValorVenda());
				  itemVenda.setProduto(produto);
				  itemVenda.setVenda(this.getVenda());
		  		
			  	itemVenda.setValorTotal(itemVenda.getQuantidadeItem()*produto.getValorVenda());
			  	this.venda.getItemVenda().add(itemVenda);
			  	/*Insere um novo item*/
			  	setItemVenda(vendaService.adicionarItemVenda(itemVenda));
		  	}
			/*Atualizar o valor total*/
			calcularValorTotalVenda();
	  }
   }

	public void consultarProduto(){
		if(!getCodigoBarras().equals(null) || getQuantidade() != null){
			adicionarItemVenda(this.produtoService.porCodigoBarra(codigoBarras));
			/*Limpa dados do formulário*/
			this.setCodigoBarras(null);
			this.setQuantidade(null);
		}
	}
	
	public void adicionaListaVendaFormaPagamento(){
		vendaFormaPagamento = new VendaFormaPagamento();
		boolean validador = true;
		
		vendaFormaPagamento.setNumeroParcela(1L);
		vendaFormaPagamento.setFormaPagamento(formaPagamentoService.formaPagamentoPorCodigo(formaPagamentoSelecionada));
		
		/*Se a forma de pagamento for dinheiro não recupera bandeira*/
		if(formaPagamentoSelecionada == 1 ){
			this.setBandeiraSelecionada(0L);
		}else {
			vendaFormaPagamento.setBandeira(formaPagamentoService.bandeiraPorCodigo(bandeiraSelecionada));
		}
		vendaFormaPagamento.setVenda(this.getVenda());
		
		if(this.getValorParcela() <= 0 ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Campo valor da parcela é obrigatório!"));
			validador = false;
		}else {
			vendaFormaPagamento.setValorParcela(this.getValorParcela());
		}
		
		if(formaPagamentoSelecionada != 1 && bandeiraSelecionada == 0 ){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Selecione uma bandeira!"));
			validador = false;
		}
		
		if(validador) {
			this.venda.getListaVendaFormaPagamento().add(vendaFormaPagamento);
		}
	}
	
	public void removeVendaFormaPagamentoLista(VendaFormaPagamento vendaFormaPagamento){
		this.venda.getListaVendaFormaPagamento().remove(vendaFormaPagamento);
	}
	
	public void finalizarVenda() {
		Venda vendaAtualizada = new Venda();
		
		if(calcularValorTotalParcelaAdicionada().equals(getValorTotal())) {
			vendaAtualizada = vendaService.finalizarVenda(this.getVenda());
			if (vendaAtualizada != null) {
				this.venda = vendaAtualizada;
				setValorParcela(0.0);
				setValorTotalParcela(0.0);
			}
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Valor total da parcela diferente do valor total da venda!"));
		}
	}		
	
	public Double getConfereValorParcela() {
		if(getValorTotalParcela().equals(0.0) || getValorTotalParcela().equals(getValorTotal())) {
			return getValorTotalParcela();
		}
		//DecimalFormat df = new DecimalFormat("###########.##");
		//return Double.valueOf(df.format(getValorTotal()-getValorTotalParcela()));
		return getValorTotal()-getValorTotalParcela();
	}
	
	public String getStringConfereValorParcela() {
		Double valorParcela = getConfereValorParcela();
		if(valorParcela < 0) {
			return "Ultrapassou o valor em R$ "+(valorParcela*(-1));
		}
		
		if(valorParcela > 0){
			return "Ainda faltam R$ "+valorParcela;
		}
		return null;
	}
	
	public void novaVenda() {
		Venda venda = new Venda();
		venda = (vendaService.buscarVenda(usuarioLogado.getUsuario()));
		if(venda.getCaixa() != null) {
			setVenda(venda);
		}else {
			menuVisao.apresentacao();
		}
		/*Atualizar o valor total atual*/
		calcularValorTotalVenda();
	}
	
	public void abrirJanelaAutorizacao() {
		Janela janela = new Janela();
		janela.abrirJanela2(true, true, true, 400, 200, FuncionalidadeEnum.ABRIRCAIXA.getUrl());
	}
	
}
