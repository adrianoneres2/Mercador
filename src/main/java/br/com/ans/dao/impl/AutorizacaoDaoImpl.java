package br.com.ans.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.ans.dao.AutorizacaoDao;
import br.com.ans.model.Autorizacao;

@RequestScoped
public class AutorizacaoDaoImpl implements AutorizacaoDao{
	
	public AutorizacaoDaoImpl(){}
	
	private EntityManager entityManager;// = jpaUtil.getEntityManager();
	
	@Inject
	public AutorizacaoDaoImpl(EntityManager em){
		entityManager = em;
	}
	
	@Override
	public List<Autorizacao> todas(){
		try {
			TypedQuery<Autorizacao> query = entityManager.createQuery("FROM Autorizacao", Autorizacao.class);
			return (List<Autorizacao>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao tentar consultar tipo de venda!"));
			return null;
		}
	}

	@Override
	public Autorizacao porCodigo(Long codigoAutorizacao) {
		try {
			Autorizacao autorizacao = entityManager.find(Autorizacao.class, codigoAutorizacao);
			return autorizacao;
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao tentar consultar tipo de autorização!"));
			return null;
		}
	}

}
