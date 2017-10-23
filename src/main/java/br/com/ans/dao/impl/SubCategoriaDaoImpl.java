package br.com.ans.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.ans.dao.SubCategoriaDao;
import br.com.ans.model.SubCategoria;

@RequestScoped
public class SubCategoriaDaoImpl implements SubCategoriaDao {
	
	public SubCategoriaDaoImpl(){}
	
	private EntityManager entityManager;// = jpaUtil.getEntityManager();
	
	@Inject
	public SubCategoriaDaoImpl(EntityManager em){
		entityManager = em;
	}
	
	@Override
	public List<SubCategoria> todas(){
		
		TypedQuery<SubCategoria> query = entityManager.createQuery("from SubCategoria", SubCategoria.class);		
		
		return (List<SubCategoria>) query.getResultList();
	}
	
}
