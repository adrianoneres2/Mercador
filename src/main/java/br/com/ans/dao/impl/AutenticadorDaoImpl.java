package br.com.ans.dao.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.ans.dao.AutenticadorDao;
import br.com.ans.model.Usuario;

@RequestScoped
public class AutenticadorDaoImpl implements AutenticadorDao {

	@Inject
	private EntityManager entityManager;// = jpaUtil.getEntityManager();
	
	@Inject
	private Usuario usuario;
	
	@Override
	public Usuario validarUsuarioLogin(String email, String senha){
		
		String hql = "from Usuario where email = :email and senha = :senha";
		
		try{

			//entityManager = jpaUtil.getEntityManager();
			Query query =  entityManager.createQuery(hql);
			query.setParameter("email", email);
			query.setParameter("senha", senha.toString());

			if(!query.getResultList().isEmpty()){
				usuario = (Usuario) query.getResultList().get(0);
				return usuario;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}
