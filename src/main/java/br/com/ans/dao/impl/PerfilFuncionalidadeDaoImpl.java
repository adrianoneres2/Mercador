package br.com.ans.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.ans.dao.PerfilFuncionalidadeDao;
import br.com.ans.model.PerfilFuncionalidade;

@RequestScoped
public class PerfilFuncionalidadeDaoImpl extends GenericoDaoImpl<PerfilFuncionalidade> implements PerfilFuncionalidadeDao{

	private EntityManager entityManager;// = jpaUtil.getEntityManager();
	
	@Inject
	public PerfilFuncionalidadeDaoImpl(EntityManager em){
		entityManager = em;
	}
	
	@Override
	public List<PerfilFuncionalidade> listarPerfil(Long codigoPerfil){
		
		String hql = "from PerfilFuncionalidade pf where pf.perfil.codigoPerfil = "+codigoPerfil;
		
		List<PerfilFuncionalidade> listaPerfilFuncionalidade = new ArrayList<PerfilFuncionalidade>(); 
		
		try {
			
			TypedQuery<PerfilFuncionalidade> query = entityManager.createQuery(hql, PerfilFuncionalidade.class);
			listaPerfilFuncionalidade = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaPerfilFuncionalidade;
	}
	
	@Override
	public boolean perfilFuncionalidade(Long codigoPerfil, Long codFuncionalidade){
		String hql = "from PerfilFuncionalidade pf where pf.perfil.codigoPerfil = "+codigoPerfil+" and pf.funcionalidade.codigoFuncionalidade = "+codFuncionalidade; 
		
		try {
			TypedQuery<PerfilFuncionalidade> query = entityManager.createQuery(hql, PerfilFuncionalidade.class);
			
			if (query.getResultList().isEmpty()){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Usuário sem permissão de acesso!"));
				return false;
			}
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Erro desconhecido!"));
			return false;
		}
	}
	
	
}
