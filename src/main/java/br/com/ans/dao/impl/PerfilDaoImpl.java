package br.com.ans.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.ans.dao.PerfilDao;
import br.com.ans.dominio.Perfil;

@RequestScoped
public class PerfilDaoImpl implements PerfilDao {

	public PerfilDaoImpl(){}
	
	private EntityManager entityManager;// = jpaUtil.getEntityManager();
	
	@Inject
	public PerfilDaoImpl(EntityManager em){
		entityManager = em;
	}
	
	@Override
	public List<Perfil> obterPerfil(){
		
		TypedQuery<Perfil> query = entityManager.createQuery("from Perfil", Perfil.class);		
		
		return (List<Perfil>) query.getResultList();
	}
	
	@Override
	public Perfil obterPerfilPorCodigo(Long codigo){
		return (Perfil) entityManager.find(Perfil.class, codigo);
	}
	
}
