package br.com.ans.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao tentar consulta situação de venda!"));	
			return null;
		}
	}

	@Override
	public SituacaoVenda porCodigo(Long codigoSituacao) {
		try {
			SituacaoVenda situacaoVenda = entityManager.find(SituacaoVenda.class, codigoSituacao);
			return situacaoVenda;
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao tentar consulta situação de venda por código!"));
			return null;
		}
	}


}
