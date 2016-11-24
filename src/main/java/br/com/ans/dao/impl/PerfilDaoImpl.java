package br.com.ans.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.ans.dao.PerfilDao;
import br.com.ans.dominio.Perfil;
import util.jpaUtil;

@RequestScoped
public class PerfilDaoImpl implements PerfilDao {

	private EntityManager entityManager = jpaUtil.getEntityManager();
	
	@Override
	public List<Perfil> obterPerfil(){
		
		TypedQuery<Perfil> query = entityManager.createQuery("from Perfil", Perfil.class);		
		
		return (List<Perfil>) query.getResultList();
	}
}
