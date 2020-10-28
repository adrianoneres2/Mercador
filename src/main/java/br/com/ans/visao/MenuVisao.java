package br.com.ans.visao;

import static enumerations.FuncionalidadeEnum.APLICACAO;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ans.model.Usuario;
import br.com.ans.service.PerfilFuncionalidadeService;
import enumerations.FuncionalidadeEnum;

@Named
//@RequestScoped
@javax.faces.view.ViewScoped
public class MenuVisao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PerfilFuncionalidadeService perfilFuncionalidadeService;
	
	@Inject
	private AutenticadorVisao usuarioLogado;
	
	private String paginaAtual = "/login.xhtml";
	
	@PostConstruct
	public void init() {
		if(usuarioLogado.getUsuario().getCodigoUsuario() == null) {
			login();
		}else {
			if(getPaginaAtual() == "/login.xhtml") {
				apresentacao();
			}
		}
	}
	
	public String acessar(Usuario UsuarioLogado, FuncionalidadeEnum funcionalidadeEnum){		
		if(FuncionalidadeEnum.APLICACAO.equals(funcionalidadeEnum) ){
		   return APLICACAO.getUrl().toString();
		}else{
			Long codigoFuncionalidade = (long) funcionalidadeEnum.getCodigo();
			
			/*Verifica se o usuário passado como parâmetro tem permissão de acesso a funcionalidade solicitada.*/
			if (perfilFuncionalidadeService.perfilFuncionalidade(UsuarioLogado.getPerfil().getCodigoPerfil(), codigoFuncionalidade)){
			
				/*Se sim, devolve a url solicitada!*/
			switch(funcionalidadeEnum){
					case ALTERAUSUARIO          :return FuncionalidadeEnum.ALTERAUSUARIO.getUrl();
					case CADASTROUSUARIO        :return FuncionalidadeEnum.CADASTROUSUARIO.getUrl();
					case CONSULTAUSUARIO        :return FuncionalidadeEnum.CONSULTAUSUARIO.getUrl();
					case APLICACAO              :return FuncionalidadeEnum.APLICACAO.getUrl();
					case CADASTROPRODUTO        :return FuncionalidadeEnum.CADASTROPRODUTO.getUrl();
					case VENDA                  :return FuncionalidadeEnum.VENDA.getUrl();
					case COMPRA                 :return FuncionalidadeEnum.COMPRA.getUrl();
					case CADASTROCLIENTE        :return FuncionalidadeEnum.CADASTROCLIENTE.getUrl();
					case ATIVARINATIVARUSUARIO  :return FuncionalidadeEnum.ATIVARINATIVARUSUARIO.getUrl();
					case GERENCIARPERFIL        :return FuncionalidadeEnum.GERENCIARPERFIL.getUrl();
					case ATIVARINATIVARPERFIL   :return FuncionalidadeEnum.ATIVARINATIVARPERFIL.getUrl();
					case CONSULTAPRODUTO        :return FuncionalidadeEnum.CONSULTAPRODUTO.getUrl();
					case ATIVARINATIVARPRODUTO  :return FuncionalidadeEnum.ATIVARINATIVARPRODUTO.getUrl();
					case ALTERAPRODUTO          :return FuncionalidadeEnum.ALTERAPRODUTO.getUrl();
					case EDITARPERMISSAOUSUARIO :return FuncionalidadeEnum.EDITARPERMISSAOUSUARIO.getUrl();
					case ABRIRCAIXA             :return FuncionalidadeEnum.ABRIRCAIXA.getUrl();
					case FECHARCAIXA            :return FuncionalidadeEnum.FECHARCAIXA.getUrl();
				}
			}
		}
		///FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Sem permissão de acesso!"));
		return null;
	}

	public void acessarFuncionalidade(FuncionalidadeEnum funcionalidadeEnum) {
		setPaginaAtual(acessar(usuarioLogado.getUsuario(), funcionalidadeEnum).concat(".xhtml"));
	}

	public void aplicacao() {
		acessarFuncionalidade(FuncionalidadeEnum.APLICACAO);
	}

	public void apresentacao() {
		setPaginaAtual("/apresentacao.xhtml");
	}
	
	public void login() {
		setPaginaAtual("/login.xhtml");
	}
	
	public String getPaginaAtual() {
		return paginaAtual;
	}

	public void setPaginaAtual(String paginaAtual) {
		this.paginaAtual = paginaAtual;
	}
	
}

