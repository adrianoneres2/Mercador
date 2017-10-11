package teste;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;

//@Entity
//@Table(name = "teste", schema = "loja")
@RequestScoped
public class TabelaTeste implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//@Id
	//@SequenceGenerator(name="sq_teste", sequenceName="loja.sq_teste", allocationSize = 1, initialValue = 1) /*, schema = "loja"*/
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sq_teste")
	//@Column(name = "id", nullable = false)
	private Long  id;
	
	//@Column(name = "texto", nullable = false)
	private String   texto;

	public TabelaTeste(){}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabelaTeste other = (TabelaTeste) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		return true;
	}
	
	
	
}
