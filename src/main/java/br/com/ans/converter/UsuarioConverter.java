package br.com.ans.converter;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ans.dao.impl.UsuarioDaoImpl;
import br.com.ans.model.Usuario;

//@FacesConverter(forClass = Perfil.class, value="perfilConverter")
@FacesConverter("usuariolConverter")
public class UsuarioConverter implements Converter {

	UsuarioDaoImpl usuarioDaoImpl;
	
	 public UsuarioConverter(){}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigoPerfil) {
		
		if(codigoPerfil != null && codigoPerfil.trim().length() > 0){
			Long codigo = Long.valueOf(codigoPerfil);
			return (Usuario) this.usuarioDaoImpl.obterUsuarioPorCodigo(codigo);

		}else{
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object objetoUsuario) {

		if (objetoUsuario != null && !objetoUsuario.equals("")) {
			Usuario usuario = (Usuario) objetoUsuario;
			return usuario.getCodigoUsuario().toString();
		}else{return null;}
		
	}
}
