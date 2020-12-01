package br.com.ans.service;

import java.util.List;

import br.com.ans.model.Bandeira;
import br.com.ans.model.FormaPagamento;

public interface FormaPagamentoService {

	public List<FormaPagamento> todas();
	
	public List<Bandeira> bandeiras();

}
