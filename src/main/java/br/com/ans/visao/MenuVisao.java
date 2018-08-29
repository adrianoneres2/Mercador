package br.com.ans.visao;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ans.model.Usuario;
import br.com.ans.service.PerfilFuncionalidadeService;
import enumerations.FuncionalidadeEnum;

@Named
@RequestScoped
public class MenuVisao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PerfilFuncionalidadeService perfilFuncionalidadeService;
	
	public String acessar(Usuario UsuarioLogado, FuncionalidadeEnum funcionalidadeEnum){		
		if(FuncionalidadeEnum.APLICACAO.equals(funcionalidadeEnum) ){
		   return funcionalidadeEnum.APLICACAO.getUrl().toString();
		}else{
			Long codigoFuncionalidade = (long) funcionalidadeEnum.getCodigo();
			if (perfilFuncionalidadeService.perfilFuncionalidade(UsuarioLogado.getPerfil().getCodigoPerfil(), codigoFuncionalidade)){
			
			switch(funcionalidadeEnum){
					case ALTERAUSUARIO    :return FuncionalidadeEnum.ALTERAUSUARIO.getUrl();
					case CADASTROUSUARIO  :return FuncionalidadeEnum.CADASTROUSUARIO.getUrl();
					case CONSULTAUSUARIO  :return FuncionalidadeEnum.CONSULTAUSUARIO.getUrl();
					case APLICACAO        :return FuncionalidadeEnum.APLICACAO.getUrl();
					case CADASTROPRODUTO :return FuncionalidadeEnum.CADASTROPRODUTO.getUrl();
					case VENDA            :return FuncionalidadeEnum.VENDA.getUrl();
					case COMPRA           :return FuncionalidadeEnum.COMPRA.getUrl();
					case CADASTROCLIENTE  :return FuncionalidadeEnum.CADASTROCLIENTE.getUrl();
				}
			}
		}
		return null;
	}
	
}

