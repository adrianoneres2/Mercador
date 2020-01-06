package br.com.ans.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.ans.dao.FormaPagamentoDao;
import br.com.ans.model.FormaPagamento;

@RequestScoped
public class FormaPagamentoDaoImpl implements FormaPagamentoDao{
	
	public FormaPagamentoDaoImpl(){}
	
	private EntityManager entityManager;// = jpaUtil.getEntityManager();
	
	@Inject
	public FormaPagamentoDaoImpl(EntityManager em){
		entityManager = em;
	}
	
	@Override
	public List<FormaPagamento> todas(){
		try {
			TypedQuery<FormaPagamento> query = entityManager.createQuery("FROM FormaPagamento", FormaPagamento.class);
			return (List<FormaPagamento>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
