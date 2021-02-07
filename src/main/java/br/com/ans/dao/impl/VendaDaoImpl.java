package br.com.ans.dao.impl;


import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.ans.dao.VendaDao;
import br.com.ans.model.FormaPagamento;
import br.com.ans.model.ItemVenda;
import br.com.ans.model.SituacaoVenda;
import br.com.ans.model.Usuario;
import br.com.ans.model.Venda;
import br.com.ans.model.VendaFormaPagamento;
import br.com.ans.service.SituacaoVendaService;

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
				+ " and venda.usuarioCliente.codigoUsuario = :idUsuario"
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro interno ao tentar recupearar a venda!"));
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

	@Override
	public Venda atualizar(Venda venda) {
		try {
		 venda = this.salvar(venda);
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Venda atualizada com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao tentar gravar o registro	!"));
			return null;
		}
		return venda;
	}
	
	@Override
	public Venda finalizarVenda(Venda venda) {

		entityManager.getTransaction().begin();

		try {
			/* Persiste itens da venda */
			for (ItemVenda itemVenda : venda.getListaItemVenda()) {
				if (itemVenda.getId() == null) {
					entityManager.persist(itemVenda);
				} else {
					itemVenda = entityManager.merge(itemVenda);
				}
			}

			/* Persiste formas de pagamento */
			for (VendaFormaPagamento vendaFormaPagamento : venda.getListaVendaFormaPagamento()) {
				if (vendaFormaPagamento.getId() == null) {
					entityManager.persist(vendaFormaPagamento);
				} else {
					vendaFormaPagamento = entityManager.merge(vendaFormaPagamento);
				}
			}

			/* Atualiza venda */
			entityManager.merge(venda);
			entityManager.getTransaction().commit();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Venda finalizada com sucesso!"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao tentar finalizar a venda!"));
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		return venda;
	}
	
}
