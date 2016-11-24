package teste;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.ans.model.Usuario;
import util.jpaUtil;

@Named
@RequestScoped
public class teste implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private List<Usuario> usuarios;
	
	@Inject 
	TabelaTeste tabela;
	
	@Inject
	private CDILixo lixo;
	
	public teste(){
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void mostrarNome(){
		
		System.out.println(this.getNome());
	}
	
	public CDILixo getLixo() {
		return lixo;
	}

	public void setLixo(CDILixo lixo) {
		this.lixo = lixo;
	}

	public void testeJpa(){
	
		this.getLixo().setNome(this.getNome());
	
		System.out.println(this.getLixo().getNome());
		this.getLixo().testeMetodo();
		
		EntityManager em = jpaUtil.getEntityManager();
			
		TypedQuery<Usuario> query = em.createQuery("from Usuario", Usuario.class);
		 	this.usuarios = query.getResultList();
		 em.close();
		 
		 
		 listarUsuarios();
	}
	
	public void listarUsuarios(){
		
		for(Usuario u :usuarios){
			System.out.println(u.getNomeUsuario());
		}
		
	}
	
}