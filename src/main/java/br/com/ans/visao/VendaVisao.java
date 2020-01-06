package br.com.ans.visao;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ans.model.Caixa;
import br.com.ans.model.Usuario;
import br.com.ans.service.CaixaService;
import br.com.ans.service.UsuarioService;
import enumerations.FuncionalidadeEnum;

//@RequestScoped
//@SessionScoped

@javax.faces.view.ViewScoped
@Named
public class VendaVisao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Caixa caixa;
	
	@Inject
	private CaixaService caixaService;

	@Inject
	private UsuarioService usuarioService;

	private Usuario usuarioCliente;

	@Inject
	MenuVisao menuVisao;	
	@Inject
	private AutenticadorVisao usuarioLogado;
	
	public VendaVisao() {
	}


	@PostConstruct
	public void init() {
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


	public String acessarFuncionalidade(FuncionalidadeEnum funcionalidadeEnum){
		String retorno = menuVisao.acessar(usuarioLogado.getUsuario(), funcionalidadeEnum);
		this.caixa = caixaService.getCaixaAberto(usuarioLogado.getUsuario());
		if(retorno == null || caixa == null){
			return null;
		}
		return retorno;
	}
}
