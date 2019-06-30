package util;

import java.util.Map;

import org.primefaces.context.RequestContext;

public class Janela {
	
	 public void abrirJanela(Map<String, Object> opcoes, String url){
		 RequestContext.getCurrentInstance().openDialog("/visao/caixa/aberturaCaixa", opcoes, null);
	 }
}
