package br.com.ans.visao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import util.Janela;
import br.com.ans.model.Caixa;
import br.com.ans.model.Usuario;
import br.com.ans.service.AutenticadorService;
import br.com.ans.service.CaixaService;

//@RequestScoped
//@SessionScoped

@javax.faces.view.ViewScoped
@Named
public class CaixaVisao implements Serializable {

	private static final long serialVersionUID = 1L;

	String aberturaCaixa = "/visao/caixa/aberturaCaixa";

	@Inject
	private Caixa caixa;
	
	@Inject
	private Usuario usuarioAutorizador;
	
	@Inject
	private AutenticadorService autenticadorService;
	
	private CaixaService caixaService;
	
	public CaixaVisao() {
	}

	@PostConstruct
	public void init() {
	}
	
	public Usuario getUsuarioAutorizador() {
		return usuarioAutorizador;
	}

	public void setUsuarioAutorizador(Usuario usuarioAutorizador) {
		this.usuarioAutorizador = usuarioAutorizador;
	}

	public void abrirJanelaAberturaCaixa() {

		Map<String, Object> opcoes = new HashMap<>();

		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 250);

		Janela janela = new Janela();
		janela.abrirJanela(opcoes, aberturaCaixa);
	}

	public String logarAutorizacao(){
		usuarioAutorizador = autenticadorService.validarLogin(usuarioAutorizador) ;
		return aberturaCaixa;
	}
	
	public void abrirCaixa() {
		caixaService.abrirCaixa(caixa);
	}

}
