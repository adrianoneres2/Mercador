package br.com.ans.visao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ans.model.Caixa;
import br.com.ans.model.FormaPagamento;
import br.com.ans.model.Usuario;
import br.com.ans.model.Venda;
import br.com.ans.service.FormaPagamentoService;
import br.com.ans.service.VendaService;
import enumerations.FuncionalidadeEnum;

//@RequestScoped
//@SessionScoped

@RequestScoped
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
	VendaService vendaService;
	
	public VendaVisao() {
	}

	@PostConstruct
	public void init() {
		this.carregarListaFormaPagamento();
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
	
}
