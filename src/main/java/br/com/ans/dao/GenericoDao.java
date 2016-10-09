package br.com.ans.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.ans.modelo.EntidadeBase;

public class GenericoDao<T extends EntidadeBase> {

	/**
	   * Método utilizado para obter o entity manager.
	   * @return
	   */
	  public EntityManager getEntityManager() {
	    EntityManagerFactory factory = null;
	    EntityManager entityManager = null;
	    try {
	      //Obtém o factory a partir da unidade de persistência.
	      factory = Persistence.createEntityManagerFactory("mercadorPU");
	      //Cria um entity manager.
	      entityManager = factory.createEntityManager();
	      //Fecha o factory para liberar os recursos utilizado.
	    } finally {
	      factory.close();
	    }
	    return entityManager;
	  }


	  /**
	   * Método utilizado para salvar ou atualizar as informações de uma pessoa.
	   * @return
	   * @throws java.lang.Exception
	   */
	  public T salvar(T t) throws Exception {
	    EntityManager entityManager = getEntityManager();
	    try {
	      // Inicia uma transação com o banco de dados.
	      entityManager.getTransaction().begin();
	      // Verifica se o registro ainda não está salva no banco de dados.
	      if(t.getId() == null) {
	        //Salva os dados da pessoa.
	        entityManager.persist(t);
	      } else {
	        //Atualiza os dados.
	        t = entityManager.merge(t);
	      }
	      // Finaliza a transação.
	      entityManager.getTransaction().commit();
	    } finally {
	      entityManager.close();
	    }
	    return t;
	  }

	  /**
	   * Método que apaga a pessoa do banco de dados.
	   * @param id
	   */
	  public void excluir(Class<T> clazz, Long id) {
	    EntityManager entityManager = getEntityManager();
	    try {
	      // Inicia uma transação.
	      entityManager.getTransaction().begin();
	      // Consulta um registro na base de dados através do seu ID.
	      T t = entityManager.find(clazz, id);
	      System.out.println("Excluindo :" + t.getId());
	      // Remove um registro da base de dados.
	      entityManager.remove(t);
	      // Finaliza a transação.
	      entityManager.getTransaction().commit();
	    } finally {
	      entityManager.close();
	    }
	  }

	  /**
	   * Consulta o pessoa pelo ID.
	   * @param id
	   * @return o objeto Pessoa.
	   */
	  public T consultarPorId(Class<T> clazz, Long id) {
	    EntityManager entityManager = getEntityManager();
	    T t = null;
	    try {
	      //Consulta uma pessoa pelo seu ID.
	      t = entityManager.find(clazz, id);
	    } finally {
	      entityManager.close();
	    }
	    return t;
	  }  
}
