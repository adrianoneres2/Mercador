package br.com.ans.converter;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ans.dao.impl.PerfilDaoImpl;
import br.com.ans.dominio.Perfil;

//@FacesConverter(forClass = Perfil.class, value="perfilConverter")
@FacesConverter("perfilConverter")
public class PerfilConverter implements Converter {

	PerfilDaoImpl perfilDaoImpl;
	
	 public PerfilConverter(){}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigoPerfil) {
		
		if(codigoPerfil != null && codigoPerfil.trim().length() > 0){
			Long codigo = Long.valueOf(codigoPerfil);
			//perfilDaoImpl = new PerfilDaoImpl();
			return (Perfil) this.perfilDaoImpl.obterPerfilPorCodigo(codigo);

		}else{
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object objetoPerfil) {

		if (objetoPerfil != null && !objetoPerfil.equals("")) {
			Perfil perfil = (Perfil) objetoPerfil;
			return perfil.getCodigoPerfil().toString();
		}else{return null;}
		
	}
}
