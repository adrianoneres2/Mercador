package br.com.ans.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.ans.dao.CategoriaDao;
import br.com.ans.model.Categoria;

public class CategoriaDaoImpl implements CategoriaDao{
	
	private EntityManager entityManager;// = jpaUtil.getEntityManager();
	
	@Inject
	public CategoriaDaoImpl(EntityManager em){
		entityManager = em;
	}
	
	@Override
	public List<Categoria> todas(){
		
		TypedQuery<Categoria> query = entityManager.createQuery("from Categoria", Categoria.class);		
		
		return (List<Categoria>) query.getResultList();
	}

}
