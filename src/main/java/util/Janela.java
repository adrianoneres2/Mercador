package util;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

public class Janela {

	public void abrirJanela(Map<String, Object> opcoes, String url) {
		RequestContext.getCurrentInstance().openDialog(url, opcoes, null);
	}

	public void fecharJanela(String urlTela) {
		RequestContext.getCurrentInstance().closeDialog(urlTela);
	}

	public void abrirJanela2(Boolean redimencionar, Boolean moverTela, Boolean modal, Integer altura,
			Integer alturaConteudo, String url) {

		Map<String, Object> opcoes = new HashMap<>();

		try {
			opcoes.put("resizable", redimencionar);
			opcoes.put("draggable", moverTela);
			opcoes.put("modal", modal);
			opcoes.put("height", altura);
			opcoes.put("contentHeight", alturaConteudo);

			RequestContext.getCurrentInstance().openDialog(url, opcoes, null);

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info",
					"erro!"));
		}

	}

}
