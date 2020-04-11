package br.com.ans.dao.impl;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.ans.dao.VendaDao;
import br.com.ans.model.Usuario;
import br.com.ans.model.Venda;

@RequestScoped
public class VendaDaoImpl extends GenericoDaoImpl<Venda> implements VendaDao {

	private EntityManager entityManager;// = jpaUtil.getEntityManager();

	public VendaDaoImpl(){}
	
	@Inject
	public VendaDaoImpl(EntityManager em) {
		entityManager = em;
	}

	@Override
	public Venda buscarVenda(Usuario usuarioLogado) {

		//String hql = "from Venda v where 1=1";
		
		
		String hql = "select venda from Venda as venda " 
				+ " where 1=1"
				+ " and venda.UsuarioCliente.codigoUsuario = :idUsuario "
				+ " and venda.situacaoVenda = 2 "; 	
				
		/*
											 * Quando a situação da venda for
											 * "2-em aberto" para um determinado
											 * usuário.
											 */
		try {

			Long idUsuario = usuarioLogado.getCodigoUsuario();

			Query query = entityManager.createQuery(hql);
			query.setParameter("idUsuario", idUsuario);

			if (!query.getResultList().isEmpty()) {
				Venda venda = (Venda) query.getResultList().get(0);
				return venda;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Venda novaVenda(Venda venda) {
		// TODO Auto-generated method stub
		return null;
	}

}
