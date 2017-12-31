package br.com.ans.visao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ans.model.PerfilFuncionalidade;
import br.com.ans.model.Usuario;
import br.com.ans.service.PerfilFuncionalidadeService;

@Named
@RequestScoped
public class MenuVisao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String CADASTRO_USUARIO = "/visao/usuario/cadastroUsuario";
	private static final String CONSULTA_USUARIO = "/visao/usuario/consultaUsuario";
	private static final String ALTERAR_USUARIO = "/visao/usuario/alterarUsuario";
	private static final String APLICACAO = "/aplicacao";
	private static final String CADASTRO_PRODUTO = "/visao/produto/cadastroProduto";
	
	private Usuario usuario;
	
	@Inject
	private PerfilFuncionalidadeService perfilFuncionalidadeService;
	
	public String irCadastroUsuario(){
		
	 return CADASTRO_USUARIO;	
	
	}

	public String irConsultaUsuario(){
		 return CONSULTA_USUARIO;	
	}

	public String irAlterarUsuario(Usuario usuario){
		
		this.usuario = usuario;
		
		if(existeFuncionalidade()){
			return ALTERAR_USUARIO;
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Não tem permissão!"));
			return "";
		}
	}
	
	public String irAplicacao(){
		 return APLICACAO;	
	}
	
	public String irCadastroProduto(){
		 return CADASTRO_PRODUTO;	
	}
	
	public boolean existeFuncionalidade(){
		
		List <PerfilFuncionalidade> pfs = perfilFuncionalidadeService.porPerfil(usuario.getPerfil().getCodigoPerfil());
		boolean retorno = false;
		
		for(PerfilFuncionalidade pf : pfs){
			if(pf.getFuncionalidade().getCodigoFuncionalidade() == 7){
				retorno = true;
				break;
			}
		}
		return retorno;
	}
	
}
