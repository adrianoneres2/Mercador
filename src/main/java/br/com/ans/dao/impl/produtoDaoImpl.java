package br.com.ans.dao.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.ans.dao.ProdutoDao;
import br.com.ans.model.Produto;

@RequestScoped
public class produtoDaoImpl extends GenericoDaoImpl<Produto> implements ProdutoDao {

	
	private EntityManager entityManager;// = jpaUtil.getEntityManager();
	
	@Inject
	public produtoDaoImpl(EntityManager em){
		entityManager = em;
	}


	
	@Override
	public void novo(Produto produto) {
		try{
			this.salvar(produto);	
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
}
