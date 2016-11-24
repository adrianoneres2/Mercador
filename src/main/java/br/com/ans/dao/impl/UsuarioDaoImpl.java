package br.com.ans.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.ans.dao.UsuarioDao;
import br.com.ans.model.Usuario;
import util.jpaUtil;

@RequestScoped
public class UsuarioDaoImpl extends GenericoDaoImpl<Usuario> implements UsuarioDao{
	
	//@PersistenceContext
	private EntityManager entityManager = jpaUtil.getEntityManager();
	int posicaoLinha;
	
	@Override
	public List<Usuario> bucarTodos(){

		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		
		try {
		
			TypedQuery<Usuario> query = entityManager.createQuery("from Usuario", Usuario.class);
			listaUsuario = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaUsuario;
	}

	@Override
	public Usuario consultarUsuarioPorEmail(String email){

		String hql = "from Usuario where email = :email";

		try{

			//entityManager = jpaUtil.getEntityManager();
			Query query =  entityManager.createQuery(hql);
			query.setParameter("email", email);

			if(!query.getResultList().isEmpty()){
				Usuario usuario = (Usuario) query.getResultList().get(0);
				return usuario;
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			entityManager.close();
		}
		return null;
	}

	@Override
	public boolean consultarRgUsuario(Integer rg){

		String hql = "from Usuario where rg = :rg";
		boolean retorno = false;

		try{

			Query query =  entityManager.createQuery(hql);
			query.setParameter("rg", rg);

			if(!query.getResultList().isEmpty()){
				retorno = true;
			}else{
				retorno = false;
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			entityManager.close();
		}
		return retorno;
	}
	
	@Override
	public void salvarUsuario(Usuario usuario) throws Exception{
		this.salvar(usuario); 
	}

}
