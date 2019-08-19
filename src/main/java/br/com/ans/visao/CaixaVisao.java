package br.com.ans.visao;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import util.Janela;
import br.com.ans.model.Caixa;
import br.com.ans.model.Usuario;
import br.com.ans.service.CaixaService;
import br.com.ans.service.UsuarioService;

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
	private AutenticadorVisao usuarioAutenticado;
	
	@Inject
	private CaixaService caixaService;
	
	@Inject
	private UsuarioService usuarioService;
	
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
	
	public void abrirCaixa() {
		
		caixa.setUsuarioOperador(usuarioAutenticado.getUsuario());
		caixa.setUsuarioAbertura(usuarioService.obterUsuarioPorEmail(usuarioAutorizador.getEmail()));
		caixa.setDataAbertura(new Date());
		
		try {
			caixaService.abrirCaixa(caixa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
