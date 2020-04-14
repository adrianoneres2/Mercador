package br.com.ans.dao.impl;


import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
		
		/* Retorna a venda quando a situação for "(2) Em aberto" para um determinado usuário.*/
		String hql = "select venda from Venda as venda " 
				+ " where 1=1"
				+ " and venda.UsuarioCliente.codigoUsuario = :idUsuario "
				+ " and venda.situacaoVenda = 2 ";
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
		try {
		 venda = this.salvar(venda);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao tentar gravar o registro	!"));
			return null;
		}
		return venda;
	}

}
