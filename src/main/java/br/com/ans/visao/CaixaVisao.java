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
import br.com.ans.service.UsuarioService;
import enumerations.FuncionalidadeEnum;

//@RequestScoped
//@SessionScoped

@javax.faces.view.ViewScoped
@Named
public class CaixaVisao implements Serializable {

	private static final long serialVersionUID = 1L;

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

	@Inject
	private MenuVisao menuvisao;

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
		janela.abrirJanela(opcoes, FuncionalidadeEnum.ABRIRCAIXA.getUrl());
	}

	public void abrirJanelaFechamentoCaixa() {

		Map<String, Object> opcoes = new HashMap<>();

		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 250);

		Janela janela = new Janela();
		janela.abrirJanela(opcoes, FuncionalidadeEnum.FECHARCAIXA.getUrl());
	}

	public void abrirCaixa() {
		Usuario usuarioAut = new Usuario();
		usuarioAut = autenticadorService.validarLogin(usuarioAutorizador);

		if (usuarioAut != null) {
			caixa.setUsuarioOperador(usuarioAutenticado.getUsuario());
			caixa.setUsuarioAbertura(usuarioAut);

			String url = menuvisao.acessar(usuarioAut,
					FuncionalidadeEnum.ABRIRCAIXA);

			if (url != null) {
				Caixa caixaAberto = new Caixa();
				caixaAberto = caixaService.abrirCaixa(caixa);
				if (caixaAberto != null) {
					Janela janela = new Janela();
					janela.fecharJanela(FuncionalidadeEnum.ABRIRCAIXA.getUrl());
				}
			}
		}
	}

	public void fecharCaixa() {
		Usuario usuarioAut = new Usuario();
		usuarioAut = autenticadorService.validarLogin(usuarioAutorizador);

		if (usuarioAut != null) {

			caixa.setUsuarioOperador(usuarioAutenticado.getUsuario());
			caixa.setUsuarioFechamento(usuarioAut);

			String url = menuvisao.acessar(usuarioAut,
					FuncionalidadeEnum.FECHARCAIXA);

			if (url != null) {
				Caixa caixaAberto = new Caixa();
				caixaAberto = caixaService.fecharCaixa(caixa);
				Janela janela = new Janela();
				if (caixaAberto != null) {
					janela.fecharJanela(url);
				}
				//else{
					//FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("idFormFechamentoCaixa");
					//RequestContext.getCurrentInstance().update("idFormFechamentoCaixa");
					//janela.recarregarJanela(FuncionalidadeEnum.FECHARCAIXA.getUrl());
					//RequestContext requestContext = RequestContext.getCurrentInstance();
				    //requestContext.update(":idFormFechamentoCaixa");
					///janela.fecharJanela(FuncionalidadeEnum.ABRIRCAIXA.getUrl());
				//}
			}
		}
	}

}
