package br.com.ans.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ans.model.Caixa;
import br.com.ans.model.FormaPagamento;
import br.com.ans.model.Produto;
import br.com.ans.model.Usuario;
import br.com.ans.model.Venda;
import br.com.ans.service.FormaPagamentoService;
import br.com.ans.service.ProdutoService;
import br.com.ans.service.VendaService;
import enumerations.FuncionalidadeEnum;

@RequestScoped
//@SessionScoped

@Named
public class VendaVisao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Caixa caixa;

	private Usuario usuarioCliente;

	@Inject
	MenuVisao menuVisao;
	
	@Inject
	private AutenticadorVisao usuarioLogado;
	
	private List<FormaPagamento> formasPagamento;
	
	@Inject
	FormaPagamentoService formaPagamentoService;
	
	HashMap<Long,String> listaFormasPagamento;
	
	private Venda venda;
	
	@Inject
	private Produto produto;
	
	@Inject
	VendaService vendaService;
	
	List<Produto> produtos;
	
	@Inject
	private ProdutoService produtoService;
	
	private String codigoBarras;
	
	public VendaVisao() {
	}

	@PostConstruct
	public void init() {
		this.carregarListaFormaPagamento();
	//	this.setCodigoBarras("784456321546645");
	//	consultarProduto();
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

	public String acessarFuncionalidade(FuncionalidadeEnum funcionalidadeEnum){
		String retorno = menuVisao.acessar(usuarioLogado.getUsuario(), funcionalidadeEnum);
		if(retorno == null){
			return null;
		}
		setVenda(vendaService.buscarVenda(usuarioLogado.getUsuario()));
		setCaixa(getVenda().getCaixa());
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

	public void adicionarProduto(Produto produto){
		setProduto(produto);
		if(produto != null){
			if(this.getProdutos() == null){
				List<Produto> listaProdutos = new ArrayList<Produto>();
				listaProdutos.add(produto);
				this.setProdutos(listaProdutos);
			}
		}
	}
	
	public void consultarProduto(){
		if(codigoBarras != null){
			adicionarProduto(this.produtoService.porCodigoBarra(codigoBarras));
		}
	}
	
}
