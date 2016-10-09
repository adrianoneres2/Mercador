package br.com.ans.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import br.com.ans.modelo.Usuario;

public class UsuarioDao extends GenericoDao<Usuario>{
	
	@PersistenceContext
	private EntityManager entityManager;
	int posicaoLinha;
	Usuario usuario;

	public Usuario validarUsuarioLogin(String email, String senha){
		
		String hql = "from Usuario where email = :email and senha = :senha";
		
		try{

			entityManager = getEntityManager();
			Query query =  entityManager.createQuery(hql);
			query.setParameter("email", email);
			query.setParameter("senha", senha.toString());

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
	
	public List<Usuario> bucarTodosOsUsuarios(){
		
		StringBuilder query = new StringBuilder();
		
		query.append(" select ");
		query.append(" id_usuario, ");
		query.append(" nm_usuario, ");
		query.append(" nm_senha, ");
		query.append(" dt_cadastro, ");
		query.append(" ds_complemento, ");
		query.append(" ds_referencia, ");
		query.append(" nr_telefone_fixo, ");
		query.append(" nr_telefone_celular, ");
		query.append(" nm_sexo, ");
		query.append(" nr_rg, ");
		query.append(" dt_nascimento, ");
		query.append(" nm_email, ");
		query.append(" st_usuario_ativo ");
		query.append(" from loja.tb_usuario; ");

		entityManager = getEntityManager();
		final SQLQuery sqlQuery = ((Session) entityManager.getDelegate()).createSQLQuery(query.toString());

		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		@SuppressWarnings("unchecked")
		List<Object[]> list = sqlQuery.list();

		try {
			
			for (Object[] linha : list){
				
				posicaoLinha = 0;						
				usuario = new Usuario();
				
				usuario.setCodigoUsuario(((BigDecimal) linha[posicaoLinha++]).longValue());
				usuario.setNomeUsuario((String) linha[posicaoLinha++]);
				usuario.setSenha((String) linha[posicaoLinha++]);
				usuario.setDataCadastro((Date) linha[posicaoLinha++]);
				usuario.setComplemento((String) linha[posicaoLinha++]);
				usuario.setReferencia((String) linha[posicaoLinha++]);
				usuario.setTelefoneFixo((String) linha[posicaoLinha++]);
				usuario.setTelefoneCelular((String) linha[posicaoLinha++]);
				usuario.setSexo((String) linha[posicaoLinha++]);
				usuario.setRg(((BigDecimal) linha[posicaoLinha++]).intValue());
				usuario.setDataNascimento((Date) linha[posicaoLinha++]);
				usuario.setEmail((String) linha[posicaoLinha++]);
				usuario.setUsuarioAtivo((String) linha[posicaoLinha++]);
				
				listaUsuario.add(usuario);
				posicaoLinha++;				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaUsuario;
	}

	public Usuario consultarUsuarioPorEmail(String email){

		String hql = "from Usuario where email = :email";

		try{

			entityManager = getEntityManager();
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

	public boolean consultarRgUsuario(Integer rg){

		String hql = "from Usuario where rg = :rg";
		boolean retorno = false;

		try{

			entityManager = getEntityManager();
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

}
