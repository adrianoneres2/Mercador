package util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ObjetoSessao {

	HttpServletRequest req;
	FacesContext fc = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
 
	
	
	public Long getAtributo(String identificador) {
		return (Long) session.getAttribute(identificador);
	}
	public void setAtributo(Long atributo, String identificador) {
		session.setAttribute(identificador, atributo); 
	}
	
	public void removeAtributo(String identificador){
		session.removeAttribute(identificador);
	}
}