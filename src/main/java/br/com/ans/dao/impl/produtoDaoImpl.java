package br.com.ans.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.ans.dao.ProdutoDao;
import br.com.ans.model.Produto;
import br.com.ans.model.Usuario;

@SuppressWarnings("cdi-ambiguous-dependency")
@RequestScoped
public class produtoDaoImpl extends GenericoDaoImpl<Produto> implements ProdutoDao {

	
	private EntityManager entityManager;// = jpaUtil.getEntityManager();
	
	@Inject
	public produtoDaoImpl(EntityManager em){
		entityManager = em;
	}
	
	@Override
	public boolean novo(Produto produto) {
		try{
			this.salvar(produto);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}		
	}
	
	@Override
	public List<Produto> consultarProdutoPorNome(String nome) {
		
			String hql = "from Produto where nomeProduto like '%"+nome+"%'";
			List<Produto> listaProduto = new ArrayList<Produto>();
			
			try {
				
				TypedQuery<Produto> query = entityManager.createQuery(hql, Produto.class);
				listaProduto = query.getResultList();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return listaProduto;
	}
	
	@Override
	public List<Produto> bucarTodos(){

		List<Produto> listaProduto = new ArrayList<Produto>();
		
		try {
		
			TypedQuery<Produto> query = entityManager.createQuery("from Produto u ORDER BY nomeProduto asc", Produto.class);
			listaProduto = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaProduto;
	}

	@Override
	public Produto porCodigoBarra(String codigoBarras) {
			String hql = "from Produto where codigoBarra = :codigoBarras";

			try{
				//entityManager = jpaUtil.getEntityManager();
				Query query =  entityManager.createQuery(hql);
				query.setParameter("codigoBarras", codigoBarras);

				if(!query.getResultList().isEmpty()){
					Produto produto = (Produto) query.getResultList().get(0);
					return produto;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}

	@Override
	public Produto porCodigo(Long codigo) {
			String hql = "from Produto where codigoProduto = :codigo";

			try{
				//entityManager = jpaUtil.getEntityManager();
				Query query =  entityManager.createQuery(hql);
				query.setParameter("codigo", codigo);

				if(!query.getResultList().isEmpty()){
					Produto produto = (Produto) query.getResultList().get(0);
					return produto;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
	
}
