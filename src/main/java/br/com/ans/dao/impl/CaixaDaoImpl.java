package br.com.ans.dao.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.ans.dao.CaixaDao;
import br.com.ans.model.Caixa;
import br.com.ans.model.Usuario;

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
	
	
	@Override
	public Caixa getCaixaAberto(Usuario usuarioOperador){

		String hql = "from Caixa c where c.usuarioOperador = :usuarioOperador and  dataFechamento is null";

		try{

			Query query =  entityManager.createQuery(hql);
			query.setParameter("usuarioOperador", usuarioOperador);

			if(!query.getResultList().isEmpty()){
				Caixa caixa = (Caixa) query.getResultList().get(0);
				return caixa;
			}else{
				return null;
			}

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
