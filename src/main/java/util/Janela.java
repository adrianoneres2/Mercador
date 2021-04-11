package util;

import java.util.HashMap;
import java.util.Map;

import org.primefaces.context.RequestContext;

public class Janela {

	public void abrirJanela(Map<String, Object> opcoes, String url) {
		RequestContext.getCurrentInstance().openDialog(url, opcoes, null);
	}

	public void fecharJanela(String urlTela) {
		RequestContext.getCurrentInstance().closeDialog(urlTela);
	}

	public void abrirJanela2(Boolean redimencionar, Boolean moverTela, Boolean modal, Integer altura, Integer alturaConteudo,  String url) {
		
		Map<String, Object> opcoes = new HashMap<>();

		opcoes.put("resizable",     redimencionar);
		opcoes.put("draggable",     moverTela);
		opcoes.put("modal",         modal);
		opcoes.put("height",        altura);
		opcoes.put("contentHeight", alturaConteudo);
		
		RequestContext.getCurrentInstance().openDialog(url, opcoes, null);
	}

}
