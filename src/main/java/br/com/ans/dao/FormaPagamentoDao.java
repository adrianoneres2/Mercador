package br.com.ans.dao;

import java.util.List;

import br.com.ans.model.Bandeira;
import br.com.ans.model.FormaPagamento;

public interface FormaPagamentoDao {

	List<FormaPagamento> todas();

	List<Bandeira> bandeiras();

}
