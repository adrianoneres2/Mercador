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
import br.com.ans.service.AutenticadorService;
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
	
	@Inject
	private AutenticadorService autenticadorService;

	private Usuario usuario;
	
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
				
		    Usuario usuarioAut = new Usuario();
		    usuarioAut = autenticadorService.validarLogin(usuarioAutorizador);
		    
		    if (usuarioAut != null){
				caixa.setUsuarioOperador(usuarioAutenticado.getUsuario());
				caixa.setUsuarioAbertura(usuarioAut);
				caixa.setDataAbertura(new Date());		
				
				Caixa caixaAberto = new Caixa();
				try {
					caixaAberto = caixaService.abrirCaixa(caixa);
					if (caixaAberto != null){
						Janela janela = new Janela();
						janela.fecharJanela(aberturaCaixa);	
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    } 
		}

}
