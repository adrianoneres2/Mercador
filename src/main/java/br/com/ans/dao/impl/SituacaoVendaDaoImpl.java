package br.com.ans.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.ans.dao.SituacaoVendaDao;
import br.com.ans.model.SituacaoVenda;

@RequestScoped
public class SituacaoVendaDaoImpl implements SituacaoVendaDao{
	
	public SituacaoVendaDaoImpl(){}
	
	private EntityManager entityManager;// = jpaUtil.getEntityManager();
	
	@Inject
	public SituacaoVendaDaoImpl(EntityManager em){
		entityManager = em;
	}
	
	@Override
	public List<SituacaoVenda> todas(){
		try {
			TypedQuery<SituacaoVenda> query = entityManager.createQuery("FROM SituacaoVenda", SituacaoVenda.class);
			return (List<SituacaoVenda>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
