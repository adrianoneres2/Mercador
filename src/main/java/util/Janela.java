package util;

import java.util.Map;

import org.primefaces.context.RequestContext;

public class Janela {
	
	 public void abrirJanela(Map<String, Object> opcoes, String url){
		 RequestContext.getCurrentInstance().openDialog(url, opcoes, null);
	 }
	 
	 public void fecharJanela(String urlTela){
		 RequestContext.getCurrentInstance().closeDialog(urlTela);
	 }
	 
}
