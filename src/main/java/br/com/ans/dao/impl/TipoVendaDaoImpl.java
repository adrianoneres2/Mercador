package br.com.ans.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.ans.dao.TipoVendaDao;
import br.com.ans.model.TipoVenda;

@RequestScoped
public class TipoVendaDaoImpl implements TipoVendaDao{
	
	public TipoVendaDaoImpl(){}
	
	private EntityManager entityManager;// = jpaUtil.getEntityManager();
	
	@Inject
	public TipoVendaDaoImpl(EntityManager em){
		entityManager = em;
	}
	
	@Override
	public List<TipoVenda> todas(){
		try {
			TypedQuery<TipoVenda> query = entityManager.createQuery("FROM TipoVendaDao", TipoVenda.class);
			return (List<TipoVenda>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao tentar consultar tipo de venda!"));
			return null;
		}
	}

	@Override
	public TipoVenda porCodigo(Long codigoTipoVenda) {
		try {
			TipoVenda tipoVenda = entityManager.find(TipoVenda.class, codigoTipoVenda);
			return tipoVenda;
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao tentar consultar tipo de venda por código!"));
			return null;
		}
	}

}
