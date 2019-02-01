package br.com.ans.service.impl;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.ans.dao.ProdutoDao;
import br.com.ans.model.Produto;
import br.com.ans.service.ProdutoService;

@RequestScoped
public class ProdutoServiceImpl implements ProdutoService {

	public ProdutoServiceImpl(){}
	
	boolean validador = true;
	
	@Inject
	ProdutoDao produtoDao;

	@Override
	public void novo(Produto produto) {
		if(this.validarCampos(produto)){
		  try {
		    produtoDao.novo(produto);
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Protuto cadastrado com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Erro ao tentar cadastrar o produto!"));
		}	
	  }
	}	
	
	public boolean validarCampos(Produto produto){
		
		if(produto.getSubCategoria() == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo Sub Categoria ou Categoria são obrigatório!"));
			validador = false;
		}

		if(produto.getNomeProduto().equals("")|| produto.getNomeProduto() == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo Nome é obrigatório!"));
			validador = false;
		}

		if(produto.getQuantidadeProduto() == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo Quantidade é obrigatório!"));
			validador = false;
		}

		if(produto.getValorVenda() == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Campo Valor é obrigatório!"));
			validador = false;
		}
		return validador;
	}
}
