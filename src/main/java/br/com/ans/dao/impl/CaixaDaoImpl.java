package br.com.ans.dao.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.ans.dao.CaixaDao;
import br.com.ans.model.Caixa;

@RequestScoped
public class CaixaDaoImpl extends GenericoDaoImpl<Caixa> implements CaixaDao {

	private EntityManager entityManager;// = jpaUtil.getEntityManager();
	
	@Inject
	public CaixaDaoImpl(EntityManager em){
		entityManager = em;
	}
	
	@Override
	public void abrirCaixa(Caixa caixa) throws Exception {
		this.salvar(caixa);
	}
}
