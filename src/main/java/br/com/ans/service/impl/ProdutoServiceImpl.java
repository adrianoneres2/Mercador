package br.com.ans.service.impl;

import java.util.List;

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
			Long codigoProduto = produto.getCodigoProduto();
		    if(produtoDao.novo(produto)){
		    	if(codigoProduto != null){
		    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Protuto alterado com sucesso!"));
		    	}else{
		    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Protuto cadastrado com sucesso! Código = "+produto.getCodigoProduto()));		    		
		    	}
		    }else{
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
		
		//if(produto.getCodigoProduto() == null){
			if(produtoDao.porCodigoBarra(produto.getCodigoBarra()) != null){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Warn", "Código de barras já existe cadastrado!"));
				validador = false;
			}
		//}
		
		return validador;
	}
	
	@Override
	public List<Produto> obterProdutoPorNome(String nome) {
		
		if (!"".equals(nome)){
			return produtoDao.consultarProdutoPorNome(nome);	
		}else{
			return produtoDao.bucarTodos();	
		}
	}

	@Override
	public Produto porCodigoBarra(String codigoBarras) {
	  return produtoDao.porCodigoBarra(codigoBarras);
	}

	@Override
	public Produto porCodigo(Long codigo) {
	  return produtoDao.porCodigo(codigo);
	}
	
	@Override
	public void ativarInativar(Produto produto){
		
		if(produto.getSituacaoProduto()==1){
			produto.setSituacaoProduto(0);	
		}else{
			produto.setSituacaoProduto(1);
		}
		
        try{
        	produtoDao.novo(produto);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Situação atualizada com sucesso!"));
        }catch(Exception e){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fatal", "Erro ao tentar atualizar o produto!"));
        	e.printStackTrace();
        }
	}
	
}
